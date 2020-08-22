package com.guohe.app.service;

import com.guohe.app.domain.SignInfo;
import com.guohe.app.domain.StuSignInfo;
import com.guohe.app.dto.GetSignInDTO;
import com.guohe.app.dto.InitiateSignInDTO;
import com.guohe.app.dto.SignInChangeDTO;
import com.guohe.app.dto.SignInInfoDTO;
import com.guohe.app.exception.CustomizeErrorCode;
import com.guohe.app.exception.CustomizeException;
import com.guohe.app.mapper.SignDataMapper;
import com.guohe.app.mapper.SignInExtMapper;
import com.guohe.app.mapper.SignInMapper;
import com.guohe.app.mapper.StuInfoMapper;
import com.guohe.app.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static java.lang.StrictMath.*;

/**
 * @program: guo_he
 * @description: 负责签到相关的service
 * @author: Mr.Deng
 * @create: 2020-08-10 18:21
 **/
@Service
public class SignInService {

    @Resource
    SignInMapper signInMapper;
    @Resource
    SignInExtMapper signInExtMapper;
    @Resource
    SignDataMapper signDataMapper;
    @Resource
    StuInfoMapper stuInfoMapper;

    double precision = 200; //签到范围,目前单位是米
    double pi = 3.1415926535897;

    /**
     * @Description: 创建签到的方法, 返回验证码, 也就是签到在数据库的ID
     * @Param: [signInInfo]
     * @Return: java.lang.Integer
     * @Author: Mr.Deng
     */
    public int createSignIn(InitiateSignInDTO signInInfo) {
        //前端传参缺失
        if (signInInfo == null || StringUtils.isEmpty(signInInfo.getLongitude())
                || StringUtils.isEmpty(signInInfo.getCreator()) || !StringUtils.isNumeric(signInInfo.getLongitude())
                || !StringUtils.isNumeric(signInInfo.getLatitude()) || signInInfo.getInterval() == null || CollectionUtils.isEmpty(signInInfo.getClasses())) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST); //学年,创建者ID,经纬度,签到时间限制,签到班级都不可以为空
        }
        SignIn signIn = new SignIn();     //新建一个签到类,用于放在数据库中
        signIn.setCreateTime(System.currentTimeMillis());       //设置创建时间
        signIn.setIsOverTime(0);        //设置未过期
        signIn.setCreator(signInInfo.getCreator());     //设置创建者
        signIn.setYear(signInInfo.getSemester());       //设置学年
        signIn.setTimeLimit(signInInfo.getInterval());       //设置签到期限,单位是分钟
        signIn.setName(signInInfo.getName());           //设置签到说明
        signIn.setLongitude(NumberUtils.toDouble(signInInfo.getLongitude())); //设置经纬度
        signIn.setLatitude(NumberUtils.toDouble(signInInfo.getLatitude()));

//        StringBuffer classes = new StringBuffer();        //设置班级
//
//        //获得需要签到的班级号
//        for (int a = 0; a < signInInfo.getClasses().size(); a++) {
//            classes.append(signInInfo.getClasses().get(a)).append(",");
//        }
//        signIn.setClasses(String.valueOf(classes));
        //获得需要签到的班级号
        signIn.setClasses(String.join(",", signInInfo.getClasses()));

        int i = signInExtMapper.insertAndGetKey(signIn);    //插入数据,这个语句会在插入时把自增的主键返回给signIn
        if (i == 0) {
            throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);   //数据插入失败异常
        }
        return signIn.getSignInId();
    }

    /**
     * @Description: 进行签到的方法
     * @Param: [signInInfo]
     * @Return: java.lang.String
     * @Author: Mr.Deng
     */
    public String SignIn(SignInInfoDTO signInInfo) {
        //前端传参缺失
        if (signInInfo == null || signInInfo.getStudentId() == null || signInInfo.getSignId() == null || signInInfo.getLatitude() == null || signInInfo.getLongitude() == null) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        } else if (Integer.parseInt(signInInfo.getSignId()) <= 0)   //签到验证码也就是签到ID小于或等于0
        {
            throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG);
        }
        SignIn signIn = signInMapper.selectByPrimaryKey(Integer.valueOf(signInInfo.getSignId()));   //获得签到本体

        //检测学生签到是否成功
        //1.检测签到ID合法性
        //2.检测签到是否有该学生,防止学生输入错误*
        //3.检查签到是或否过期*
        //4.检查签到范围*
        //5.检查是否已经签到过了*

        //检查是否已经签到过了,如果签到过了,数据不会写入数据库,我确保在一个签到内,每个学生在数据库中有且仅有一个有效数据
        SignDataExample signDataExample = new SignDataExample();
        signDataExample.createCriteria().andSignIdEqualTo(Integer.valueOf(signInInfo.getSignId())).andStuNumEqualTo(signInInfo.getStudentId());
        List<SignData> dataList = signDataMapper.selectByExample(signDataExample);
        if (dataList.size() != 0) {
            SignData signData = dataList.get(0);
            signData.setSignTime(System.currentTimeMillis());       //更新签到时间
            signData.setLatitude(Double.valueOf(signInInfo.getLatitude())); //设置经纬度
            signData.setLongitude(Double.valueOf(signInInfo.getLongitude()));
            if (dataList.get(0).getSigned() != 2)  //如果之前的签到记录只是位置不对,那么就继续往下跑
            {
                throw new CustomizeException(CustomizeErrorCode.HAVE_SIGNED);
            } else {//数据库里有一个位置不对的签到数据的情况
                //检查签到是否到期
                if (signIn.getIsOverTime() == 1) {
                    signData = dataList.get(0);
                    signData.setSigned(0);  //迟到
                    int i = signDataMapper.updateByPrimaryKey(signData);
                    if (i == 0) {
                        throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);
                    }
                    throw new CustomizeException(CustomizeErrorCode.SIGN_TIMEOUT);
                } else if (System.currentTimeMillis() - signIn.getCreateTime() > signIn.getTimeLimit() * 60000) //签到过期,但是签到本体没更新状态
                {
                    signIn.setIsOverTime(1);        //签到过期了
                    signData.setSigned(0);
                    int i = signDataMapper.updateByPrimaryKey(signData);  //签到数据插入数据库
                    if (i == 0) {
                        throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
                    }
                    int g = signInMapper.updateByPrimaryKey(signIn);        //写入数据库
                    if (g == 0) {
                        throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
                    }
                    throw new CustomizeException(CustomizeErrorCode.SIGN_TIMEOUT);
                }
                //检查是否在签到范围内
                if (distance(signInInfo, signIn) > precision) {
                    signDataMapper.updateByPrimaryKey(signData);
                    throw new CustomizeException(CustomizeErrorCode.OUT_PLACE);
                }
                signData.setSigned(1);  //成功签到
                signDataMapper.updateByPrimaryKey(signData);
                return "签到成功";
            }
        }


        //这个人之前没签到过
        SignData signData = new SignData();
        signData.setSignId(Integer.valueOf(signInInfo.getSignId()));        //设置签到的ID
        signData.setSignTime(System.currentTimeMillis());       //设置签到时间
        signData.setStuNum(signInInfo.getStudentId());      //设置学生学号
        signData.setLatitude(Double.valueOf(signInInfo.getLatitude())); //设置经纬度
        signData.setLongitude(Double.valueOf(signInInfo.getLongitude()));

        boolean pd = true;//用于判断该学生是否在签到名单内
        if (signIn == null) {
            throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG); //查询不到签到,ID有误
        } else {
            //判断该签到是否包含该学生
            if (signIn.getClasses() != null) {
                String[] classesList = signIn.getClasses().split(",");  //获得签到班级号
                String classId = signInInfo.getStudentId().substring(0, 10);    //获得学生班级号
                for (int a = 0; a < classesList.length; a++) {
                    if (classId.equals(classesList[a])) {               //判断学生班级号是否在签到班级号内
                        pd = false;
                    }
                }
            }
            if (pd) {
                throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG2);     //该学生不在签到班级内
            }

            //检查签到是否到期
            if (signIn.getIsOverTime() == 1) {
                signData.setSigned(0);
                int i = signDataMapper.insert(signData);
                if (i == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);
                }
                throw new CustomizeException(CustomizeErrorCode.SIGN_TIMEOUT);
            } else if (System.currentTimeMillis() - signIn.getCreateTime() > signIn.getTimeLimit() * 60000) //签到过期,但是签到本体没更新状态
            {
                signIn.setIsOverTime(1);        //签到过期了
                signData.setSigned(0);
                int i = signDataMapper.insert(signData);  //签到数据插入数据库
                if (i == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
                }
                int g = signInMapper.updateByPrimaryKey(signIn);        //写入数据库
                if (g == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
                }
                throw new CustomizeException(CustomizeErrorCode.SIGN_TIMEOUT);
            }

            //检查是否在签到范围内
            if (distance(signInInfo, signIn) > precision) {
                signData.setSigned(2);
                signDataMapper.insert(signData);
                throw new CustomizeException(CustomizeErrorCode.OUT_PLACE);
            }
            signData.setSigned(1);  //成功签到
            signDataMapper.insert(signData);
            return "签到成功";
        }

    }

    /**
     * @Description: 获得签到历史
     * @Param: [id]
     * @Return: java.util.List
     * @Author: Mr.Deng
     */
    public List<SignInfo> signInHistory(String id) {
        //前端传参缺失
        if (id == null || Objects.equals(id, "")) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        }

        SignInExample signInExample = new SignInExample();
        signInExample.createCriteria().andCreatorEqualTo(id);
        signInExample.setOrderByClause("createTime DESC");
        List<SignIn> signInList = signInMapper.selectByExample(signInExample);
        List<SignIn> newSignList = new ArrayList<>();
        for (int a = 0; a < signInList.size(); a++) {
            newSignList.add(updateSignInTime(String.valueOf(signInList.get(a).getSignInId())));
        }
        List<SignInfo> signInfoList = new ArrayList<>();
        for (int a = 0; a < signInList.size(); a++) {
            signInfoList.add(new SignInfo(newSignList.get(a)));
        }
        return signInfoList;

    }

    /**
     * @Description: 负责更新签到是否过期
     * @Param: [signId]
     * @Return: void
     * @Author: Mr.Deng
     */
    public SignIn updateSignInTime(String signId) {
        SignIn signIn = signInMapper.selectByPrimaryKey(Integer.valueOf(signId));    //从数据库获得签到实例
        if (signIn == null) {
            throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG);
        }
        if (System.currentTimeMillis() - signIn.getCreateTime() >= signIn.getTimeLimit() * 60000)        //改签到已经过期
        {
            signIn.setIsOverTime(1);        //更新签到状态
        }
        int i = signInMapper.updateByPrimaryKey(signIn);        //写入数据库
        if (i == 0) {
            throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
        }
        return signIn;
    }

    /**
     * @Description: 算出距离的方法
     * @Param: [longitude, latitude, longitude1, latitude1]
     * @Return: double
     * @Author: Mr.Deng
     */
    public double distance(SignInInfoDTO signInInfo, SignIn signIn) {
        if (signIn.getLongitude() == 0 && signIn.getLatitude() == 0) {
            return 0;
        }
        double c = sin(Double.parseDouble(signInInfo.getLatitude()) / 57.2958) * sin(signIn.getLatitude() / 57.2958) +
                cos(Double.parseDouble(signInInfo.getLatitude()) / 57.2958) * cos(signIn.getLatitude() / 57.2958) * cos((Double.parseDouble(signInInfo.getLongitude()) - signIn.getLongitude()) * pi / 180);
        return 6371000 * acos(c) * pi / 180;
    }


    /**
     * @Description: 获得单个签到的所有记录, 应该返回签到信息, 一个存储着已签到的人的列表, 一个没签到的列表
     * @Param: [id]
     * @Return: com.guohe.app.model.SignIn
     * @Author: Mr.Deng
     */
    public GetSignInDTO getSignIn(String id) {
        //前端传参缺失
        if (id == null || Objects.equals(id, "")) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        }

        SignIn signIn = updateSignInTime(id);
        //获得成功签到的人的记录
        SignDataExample successDataExample = new SignDataExample();
        successDataExample.createCriteria().andSignIdEqualTo(signIn.getSignInId()).andSignedEqualTo(1);
        List<SignData> successList = signDataMapper.selectByExample(successDataExample);

        //获得迟到的记录
        SignDataExample lateDataExample = new SignDataExample();
        lateDataExample.createCriteria().andSignIdEqualTo(signIn.getSignInId()).andSignedEqualTo(0);
        List<SignData> lateList = signDataMapper.selectByExample(lateDataExample);


        //获得所有需要签到的学生信息
        String[] classNum = signIn.getClasses().split(",");
        List<StuInfo> stuInfoList = new ArrayList<>();
        for (int a = 0; a < classNum.length; a++)  //获得了需要签到的班级的学生信息
        {
            stuInfoList.addAll(getStuInfoByClassNum(classNum[a]));
        }


        //获得所有没有签到记录的学生
        List<StuInfo> notList = getFailList(stuInfoList, successList, lateList);

        //获得签到成功的信息列表
        ArrayList<StuSignInfo> successInfoList = signDataToStuSignInfo(successList, stuInfoList, "1");  //成功签到

        //获得失败的信息列表,包含了迟到和没签到的
        ArrayList<StuSignInfo> failInfoList = signDataToStuSignInfo(lateList, stuInfoList, "0");    //迟到
        successInfoList.addAll(failInfoList);

        //获得旷课的名单
        ArrayList<StuSignInfo> lostList = new ArrayList<>();
        for (int a = 0; a < notList.size(); a++) {
            lostList.add(new StuSignInfo(notList.get(a).getName(), notList.get(a).getUid(), "2"));        //旷课
        }

        return new GetSignInDTO(signIn, successInfoList, lostList);
    }

    /**
     * @Description: 把signData转化为stuSignInfo
     * @Param: [signDataList, stuInfoList]
     * @Return: java.util.ArrayList<com.guohe.app.domain.StuSignInfo>
     * @Author: Mr.Deng
     */
    public ArrayList<StuSignInfo> signDataToStuSignInfo(List<SignData> signDataList, List<StuInfo> stuInfoList, String status) {
        //创建一个MAP,key为序号,value为姓名,方便后面查询
        Map<String, String> stuMap = new HashMap<>();
        for (int a = 0; a < stuInfoList.size(); a++) {
            stuMap.put(stuInfoList.get(a).getUid(), stuInfoList.get(a).getName());
        }
        //
        ArrayList<StuSignInfo> signInfoArrayList = new ArrayList<>();
        for (int a = 0; a < signDataList.size(); a++) {

            signInfoArrayList.add(new StuSignInfo(stuMap.get(signDataList.get(a).getStuNum()), signDataList.get(a).getStuNum(), status));
        }
        return signInfoArrayList;
    }

    /**
     * @Description: 获得班级所有学生信息
     * @Param: [classNum]
     * @Return: java.util.List<com.guohe.app.model.StuInfo>
     * @Author: Mr.Deng
     */
    public List<StuInfo> getStuInfoByClassNum(String classNum) {
        StuInfoExample stuInfoExample = new StuInfoExample();
        stuInfoExample.createCriteria().andClass_numEqualTo(classNum);
        return stuInfoMapper.selectByExample(stuInfoExample);
    }

    /**
     * @Description: 获得没有签到的学生信息
     * @Param: [allInfo, successList]
     * @Return: java.util.List<com.guohe.app.model.StuInfo>
     * @Author: Mr.Deng
     */
    public List<StuInfo> getFailList(List<StuInfo> allInfo, List<SignData> successList, List<SignData> lateList) {
        //获得所有已经成功签到的人的学号
        List<String> successNum = new ArrayList<>();
        for (int a = 0; a < successList.size(); a++) {
            successNum.add(successList.get(a).getStuNum());
        }

        //获得所有迟到的人的学号
        List<String> lateNum = new ArrayList<>();
        for (int a = 0; a < lateList.size(); a++) {
            lateNum.add(lateList.get(a).getStuNum());
        }

        //获得所有没有签到记录的人的信息
        List<StuInfo> notList = new ArrayList<>();
        for (int a = 0; a < allInfo.size(); a++) {
            if (successNum.contains(allInfo.get(a).getUid()) || lateNum.contains(allInfo.get(a).getUid())) {
                continue;
            } else {
                notList.add(allInfo.get(a));
            }
        }
        return notList;
    }


    /**
     * @Description: 更新签到状态
     * @Param: [signInChangeDTO]
     * @Return: java.lang.Void
     * @Author: Mr.Deng
     */
    public String changeStatus(SignInChangeDTO signInChangeDTO) {
        //前端传参缺失
        if (signInChangeDTO == null || signInChangeDTO.getSignId() == null || signInChangeDTO.getStatus() == null || signInChangeDTO.getStuId() == null) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        }
        SignIn sign = signInMapper.selectByPrimaryKey(Integer.valueOf(signInChangeDTO.getSignId()));        //获得签到本体
        SignDataExample signInExample = new SignDataExample();
        signInExample.createCriteria().andSignIdEqualTo(Integer.valueOf(signInChangeDTO.getSignId())).andStuNumEqualTo(signInChangeDTO.getStuId());
        List<SignData> signIn = signDataMapper.selectByExample(signInExample);      //获得签到记录
        if (sign == null) {
            throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG);
        } else if (signIn.size() > 1) {
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        } else if (signIn.size() == 0)       //数据库中没有数据
        {
            SignData signData = new SignData();     //新建签到数据
            signData.setStuNum(signInChangeDTO.getStuId());
            signData.setSignId(Integer.valueOf(signInChangeDTO.getSignId()));
            signData.setSignTime(System.currentTimeMillis());
            if (Objects.equals(signInChangeDTO.getStatus(), "1"))        //新增签到成功数据
            {
                signData.setSigned(1);
                int i = signDataMapper.insert(signData);
                if (i == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);
                }
            } else if (Objects.equals(signInChangeDTO.getStatus(), "0"))  //新增迟到数据
            {
                signData.setSigned(0);
                int i = signDataMapper.insert(signData);
                if (i == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);
                }
            } else if (Objects.equals(signInChangeDTO.getStatus(), "2"))    //新增旷课数据
            {
                signData.setSigned(2);
                int i = signDataMapper.insert(signData);
                if (i == 0) {
                    throw new CustomizeException(CustomizeErrorCode.SQL_INSERT_FAIL);
                }
            } else {
                throw new CustomizeException(CustomizeErrorCode.STATUS_WRONG);
            }
        } else if (signIn.size() == 1)   //已经有了数据
        {
            if (Objects.equals(signInChangeDTO.getStatus(), "1")) {     //成功
                signIn.get(0).setSigned(1);
            } else if (Objects.equals(signInChangeDTO.getStatus(), "0")) {  //迟到
                signIn.get(0).setSigned(0);
            } else if (Objects.equals(signInChangeDTO.getStatus(), "2"))       //旷课
            {
                signIn.get(0).setSigned(2);
            } else {
                throw new CustomizeException(CustomizeErrorCode.STATUS_WRONG);
            }
            int i = signDataMapper.updateByPrimaryKey(signIn.get(0));
            if (i == 0) {
                throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
            }
        }
        return "更改状态成功";
    }
}
