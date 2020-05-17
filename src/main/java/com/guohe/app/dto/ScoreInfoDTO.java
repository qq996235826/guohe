package com.guohe.app.dto;

import lombok.Data;

/**
 * @version/版本: V1.0
 * @author/作者: 邓浩然
 * @description/描述: 用于获取学生排名信息
 * @data/创建日期: 2020-05-16 22:12
 **/
@Data
public class ScoreInfoDTO
{
    private String startSemester;   //学期
    private String courseName;      //课程名
    private String score;           //该学生该科成绩
    private String examMethod;  //考试类型

}
