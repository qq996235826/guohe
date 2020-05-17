package com.guohe.app.mapper;

/**
 * @author/作者: 邓浩然
 * @description/描述:
 * @data/创建日期: 2020-05-17 11:48
 **/
public interface GpaExtMapper
{
    long gpaRankAtSemester(String uid,String schoolTerm, String gpa);


    String gpaByUidAndSemester(String uid, String schoolTerm);


}
