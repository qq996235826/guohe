package com.guohe.app.service;

import com.guohe.app.dto.GpaInfoDTO;
import com.guohe.app.dto.ResultDTO;
import com.guohe.app.dto.ScoreInfoDTO;
import com.guohe.app.exception.CustomizeErrorCode;
import com.guohe.app.exception.CustomizeException;
import com.guohe.app.mapper.GpaExtMapper;
import com.guohe.app.mapper.GpaMapper;
import com.guohe.app.mapper.ScoreExtMapper;
import com.guohe.app.mapper.ScoreMapper;
import com.guohe.app.model.Score;
import com.guohe.app.model.ScoreExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @version/版本: V1.0
 * @author/作者: 邓浩然
 * @description/描述: 查询成绩排名的service
 * @data/创建日期: 2020-05-16 21:18
 **/
@Service
public class RankService {
    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ScoreExtMapper scoreExtMapper;

    @Autowired
    private GpaMapper gpaMapper;

    @Autowired
    private GpaExtMapper gpaExtMapper;


    /**
     * @description/描述:查询单科全校排名
     * @param/参数: [courseName科目名, startSemester学期, score该科成绩,examMethod 考试还是考察]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    public ResultDTO getRank(ScoreInfoDTO info) {
        //前端传参缺失
        if (info == null || StringUtils.isBlank(info.getCourseName()) || StringUtils.isBlank(info.getScore()) || StringUtils.isBlank(info.getStartSemester())
                || StringUtils.isBlank(info.getExamMethod())) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        try {
            Long rank = scoreExtMapper.getRank(info.getCourseName(), info.getStartSemester(), info.getScore(), info.getExamMethod());
            return ResultDTO.okOf(rank + 1);
        } catch (Exception e) {
            //抛出查询失败错误
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        }
    }

    public Long getRank(String courseName, String startSemester, String score, String examMethod) {
        try {
            Long rank = scoreExtMapper.getRank(courseName, startSemester, score, examMethod);
            return rank + 1;
        } catch (Exception e) {
            //抛出查询失败错误
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        }
    }


    /**
     * @description/描述:查询绩点学院排名
     * @param/参数: [info 包含了学期,学号,绩点]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    public ResultDTO getGpaRank(GpaInfoDTO info) {
        //前端传参缺失
        if (info == null || StringUtils.isBlank(info.getUid()) || StringUtils.isBlank(info.getSemester()) || StringUtils.isBlank(info.getGpa())) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        try {
            //取出学院号
            String uid = info.getUid().substring(0, 7) + "%";
            //获得第几个学期
            String schoolTerm = "semester" + semester(uid, info.getSemester()).toString();

            //获得该同学绩点
            String gpa = gpaExtMapper.gpaByUidAndSemester(info.getUid(), schoolTerm);

            //获得排名
            Long rank = gpaExtMapper.gpaRankAtSemester(uid, schoolTerm, gpa);
            return ResultDTO.okOf(rank + 1);
        } catch (Exception e) {
            //抛出查询失败错误
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        }
    }

    /**
     * @description/描述:查询所有科目排名
     * @param/参数: [uid学号, startSemester学期]
     * @return/返回: java.util.Map<java.lang.String, java.lang.String>
     **/
    public ResultDTO getAllRank(GpaInfoDTO info) {
        //前端传参缺失
        if (info == null || StringUtils.isBlank(info.getGpa()) || StringUtils.isBlank(info.getSemester()) || StringUtils.isBlank(info.getUid())) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        try {
            //获得所有有分数的科目以及其分数
            List<Score> scoreList = scoreExtMapper.getAllScoreCanCompare(info.getUid(), info.getSemester());
            //用于存放科目及其排名
            HashMap<String, String> rankMap = new HashMap<String, String>();

            //遍历scoreList以获得各科分数和名字以及考试类型,再用getRank方法查询排名
            for (Score score : scoreList) {
                String rank = getRank(score.getCourseName(), info.getSemester(), score.getScore(), score.getExaminationMethod()).toString();
                rankMap.put(score.getCourseName(), rank);
            }
            return ResultDTO.okOf(rankMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.SQL_SEARCH_FAIL);
        }


    }


    /**
     * @description/描述:学期计算器
     * @param/参数: [uid学号, nowSemester当前学期 eg:"2019-2020-1"]
     * @return/返回: java.lang.Integer
     **/
    public ResultDTO semester(String uid, String nowSemester) {

        String substring = uid.substring(0, 2);//取学号前两位
        String[] s = nowSemester.split("-");  //拆分当前学期
        if (s.length != 3) {
            return ResultDTO.errorOf(CustomizeErrorCode.JSON_WRONG);
        }
        String s1 = s[0].substring(2);    //当前学年
        String s2 = s[2];                 //学期修正

        if (StringUtils.isNotBlank(substring)) {
            Integer start = Integer.valueOf(substring);//入学年
            Integer now = Integer.valueOf(s1);//现在学年
            Integer x = Integer.valueOf(s2);  //修正
            return ResultDTO.okOf((now - start) * 2 + x);
        } else {
            throw new CustomizeException(CustomizeErrorCode.JSON_WRONG);
        }


    }
}
