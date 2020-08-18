package com.guohe.app.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.lang.StrictMath.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: guo_he
 * @description: 负责签到相关的service
 * @author: Mr.Deng
 * @create: 2020-08-10 18:21
 **/
@Service
public class SignInService {
    @Autowired
    SignInMapper signInMapper;
    @Autowired
    SignInExtMapper signInExtMapper;
    @Autowired
    SignDataMapper signDataMapper;
    @Autowired
    StuInfoMapper stuInfoMapper;

    double precision = 200; //签到范围,目前单位是米
    double pi=3.1415926535897;

    /**
     * @Description: 创建签到的方法, 返回验证码, 也就是签到在数据库的ID
     * @Param: [signInInfo]
     * @Return: java.lang.Integer
     * @Author: Mr.Deng
     */
    public Integer createSignIn(InitiateSignInDTO signInInfo) {
        SignIn signIn = new SignIn();     //新建一个签到类,用于放在数据库中
        signIn.setCreateTime(System.currentTimeMillis());       //设置创建时间
        signIn.setIsOverTime(0);        //设置未过期
        signIn.setCreator(signInInfo.getCreator());     //设置创建者
        signIn.setYear(signInInfo.getSemester());       //设置学年
        signIn.setTime(signInInfo.getInterval());       //设置签到期限,单位是分钟
        signIn.setName(signInInfo.getName());           //设置签到说明
        signIn.setLongitude(Double.valueOf(signInInfo.getLongitude())); //设置经纬度
        signIn.setLatitude(Double.valueOf(signInInfo.getLatitude()));
        StringBuffer classes = new StringBuffer();        //设置班级
        for (int a = 0; a < signInInfo.getClasses().size(); a++) {
            classes.append(signInInfo.getClasses().get(a)).append(",");
        }
        signIn.setClasses(String.valueOf(classes));

        try {
            signInExtMapper.insertAndGetKey(signIn);    //插入数据
        } catch (Exception e) {
            e.printStackTrace();
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
        SignData signData = new SignData();
        signData.setSignId(Integer.valueOf(signInInfo.getSignId()));        //设置签到的ID
        signData.setSignTime(System.currentTimeMillis());       //设置签到时间
        signData.setStuNum(signInInfo.getStudentId());      //设置学生学号
        signData.setLatitude(Double.valueOf(signInInfo.getLatitude())); //设置经纬度
        signData.setLongitude(Double.valueOf(signInInfo.getLongitude()));

        //检测学生签到是否成功
        //1.检测签到ID合法性
        //2.检测签到是否有该学生,防止学生输入错误*
        //3.检查签到是或否过期*
        //4.检查签到范围*
        //5.检查是否已经签到过了*


        boolean pd = true;
        try {
            SignIn signIn = signInMapper.selectByPrimaryKey(Integer.valueOf(signInInfo.getSignId()));
            if (signIn == null) {
                return "验证码错误,该签到不存在";
            }
            else
            {
                //判断该签到是否包含该学生
                if(signIn.getClasses()!=null)
                {
                    String[] classesList = signIn.getClasses().split(",");
                    String classId = signInInfo.getStudentId().substring(0, 10);
                    for (int a = 0; a < classesList.length; a++) {
                        if (classId.equals(classesList[a])) {
                            pd = false;
                        }
                    }
                }
                if (pd)
                {
                    return "签到失败,你不在需要签到的名单内,检查你的验证码是否正确";
                }

                //检查是否已经签到过了
                SignDataExample signDataExample = new SignDataExample();
                signDataExample.createCriteria().andSignIdEqualTo(Integer.valueOf(signInInfo.getSignId())).andStuNumEqualTo(signInInfo.getStudentId());
                List<SignData> dataList = signDataMapper.selectByExample(signDataExample);
                if (dataList.size() != 0) {
                    return "已经签到过了";
                }
                //检查签到是否到期
                if (signIn.getIsOverTime() == 1)
                {
                    signData.setSigned(0);
                    signDataMapper.insert(signData);
                    return "签到失败,签到已过期";
                }
                else if (System.currentTimeMillis() - signIn.getCreateTime() > signIn.getTime() * 60000) //签到过期,但是签到本体没更新状态
                {
                    signIn.setIsOverTime(1);        //签到过期了
                    try {
                        signData.setSigned(0);
                        signDataMapper.insert(signData);
                        signInMapper.updateByPrimaryKey(signIn);        //写入数据库
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
                    }
                    return "签到失败,签到已过期";
                }

                //检查是否在签到范围内
                if (distance(signInInfo, signIn) > precision)
                {
                    return "签到失败,不在范围内";
                }
                signData.setSigned(1);
                signDataMapper.insert(signData);
                return "签到成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        }
    }

    /**
     * @Description: 获得签到历史
     * @Param: [id]
     * @Return: java.util.List
     * @Author: Mr.Deng
     */
    public List signInHistory(String id) {
        SignInExample signInExample = new SignInExample();
        signInExample.createCriteria().andCreatorEqualTo(id);
        return signInMapper.selectByExample(signInExample);
    }

    /**
     * @Description: 负责更新签到是否过期
     * @Param: [signId]
     * @Return: void
     * @Author: Mr.Deng
     */
    public SignIn updateSignInTime(String signId) {
        SignIn signIn = signInMapper.selectByPrimaryKey(Integer.valueOf(signId));    //从数据库获得签到实例
        if (System.currentTimeMillis() - signIn.getCreateTime() >= signIn.getTime() * 60000)        //改签到已经过期
        {
            signIn.setIsOverTime(1);        //更新签到状态
        }
        try {
            signInMapper.updateByPrimaryKey(signIn);        //写入数据库
        } catch (Exception e) {
            e.printStackTrace();
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
        if(signIn.getLongitude()==0&&signIn.getLatitude()==0)
        {
            return 0;
        }
        double c=sin(Double.parseDouble(signInInfo.getLatitude())/57.2958)*sin(signIn.getLatitude()/57.2958)+
                cos(Double.parseDouble(signInInfo.getLatitude())/57.2958)*cos(signIn.getLatitude()/57.2958)*cos((Double.parseDouble(signInInfo.getLongitude())-signIn.getLongitude())*pi/180);
        double distance=6371000*acos(c)*pi/180;
        return distance;
    }


    /**
     * @Description: 获得单个签到的所有记录,应该返回签到信息,一个存储着已签到的人的列表,一个没签到的列表
     * @Param: [id]
     * @Return: com.guohe.app.model.SignIn
     * @Author: Mr.Deng
     */
    public GetSignInDTO getSignIn(String id) {

        SignIn signIn=updateSignInTime(id);
        //获得成功签到的人的记录
        SignDataExample successDataExample=new SignDataExample();
        successDataExample.createCriteria().andSignIdEqualTo(signIn.getSignInId()).andSignedEqualTo(1);
        List<SignData> successList=signDataMapper.selectByExample(successDataExample);

        //获得迟到的记录
        SignDataExample lateDataExample=new SignDataExample();
        lateDataExample.createCriteria().andSignIdEqualTo(signIn.getSignInId()).andSignedEqualTo(0);
        List<SignData> lateList=signDataMapper.selectByExample(lateDataExample);


        //获得所有需要签到的学生信息
        String[] classNum=signIn.getClasses().split(",");
        List<StuInfo> stuInfoList = new ArrayList<>();
        for(int a=0;a<classNum.length;a++)  //获得了需要签到的班级的学生信息
        {
            stuInfoList.addAll(getStuInfoByClassNum(classNum[a]));
        }


        //获得所有没有签到记录的学生
        List<StuInfo> notList=getFailList(stuInfoList,successList,lateList);

        //获得签到成功的信息列表
        ArrayList<StuSignInfo> successInfoList=signDataToStuSignInfo(successList,stuInfoList,"成功签到");

        //获得失败的信息列表,包含了迟到和没签到的
        ArrayList<StuSignInfo> failInfoList=signDataToStuSignInfo(lateList,stuInfoList,"迟到");
        for(int a=0;a<notList.size();a++)
        {
            failInfoList.add(new StuSignInfo(notList.get(a).getName(),notList.get(a).getUid(),"旷课"));
        }


        return new GetSignInDTO(signIn,successInfoList,failInfoList);
    }

    /**
     * @Description: 把signData转化为stuSignInfo
     * @Param: [signDataList, stuInfoList]
     * @Return: java.util.ArrayList<com.guohe.app.domain.StuSignInfo>
     * @Author: Mr.Deng
     */
    public ArrayList<StuSignInfo> signDataToStuSignInfo(List<SignData> signDataList,List<StuInfo> stuInfoList,String status)
    {
        //创建一个MAP,key为序号,value为姓名,方便后面查询
        Map<String,String> stuMap=new HashMap<>();
        for(int a=0;a<stuInfoList.size();a++)
        {
            stuMap.put(stuInfoList.get(a).getUid(),stuInfoList.get(a).getName());
        }
        //
        ArrayList<StuSignInfo> signInfoArrayList=new ArrayList<>();
        for(int a=0;a<signDataList.size();a++)
        {

            signInfoArrayList.add(new StuSignInfo(stuMap.get(signDataList.get(a).getStuNum()),signDataList.get(a).getStuNum(),status));
        }
        return signInfoArrayList;
    }

    /**
     * @Description: 获得班级所有学生信息
     * @Param: [classNum]
     * @Return: java.util.List<com.guohe.app.model.StuInfo>
     * @Author: Mr.Deng
     */
    public List<StuInfo> getStuInfoByClassNum(String classNum)
    {
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
    public List<StuInfo> getFailList(List<StuInfo> allInfo, List<SignData> successList,List<SignData> lateList)
    {
        //获得所有已经成功签到的人的学号
        List<String> successNum=new ArrayList<>();
        for (int a=0;a<successList.size();a++)
        {
            successNum.add(successList.get(a).getStuNum());
        }

        //获得所有迟到的人的学号
        List<String> lateNum=new ArrayList<>();
        for (int a=0;a<lateList.size();a++)
        {
            lateNum.add(lateList.get(a).getStuNum());
        }

        //获得所有没有签到记录的人的信息
        List<StuInfo> notList=new ArrayList<>();
        for(int a=0;a<allInfo.size();a++)
        {
            if (successNum.contains(allInfo.get(a).getUid())||lateNum.contains(allInfo.get(a).getUid()))
            {
                continue;
            }
            else
            {
                notList.add(allInfo.get(a));
            }
        }
        return notList;
    }


    /**
     * @Description: 更新签到状态,改成成功签到
     * @Param: [signInChangeDTO]
     * @Return: java.lang.Void
     * @Author: Mr.Deng
     */
    public String changeStatus(SignInChangeDTO signInChangeDTO) {
        try {
            SignDataExample signInExample = new SignDataExample();
            signInExample.createCriteria().andSignIdEqualTo(Integer.valueOf(signInChangeDTO.getSignId())).andStuNumEqualTo(signInChangeDTO.getStuId());
            List<SignData> signIn=signDataMapper.selectByExample(signInExample);
            if(signIn.size()!=1)
            {
                return "更新失败";
            }
            else
            {
                signIn.get(0).setSigned(1);
            }
            signDataMapper.updateByPrimaryKey(signIn.get(0));
            return "签到成功";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.SQL_UPDATE_FAIL);
        }

    }
}
