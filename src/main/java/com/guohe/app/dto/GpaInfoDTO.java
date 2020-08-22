package com.guohe.app.dto;

import lombok.Data;

/**
 * @version/版本: V1.0
 * @author/作者: 邓浩然
 * @description/描述: 用于查询绩点排名的信息DTO, 前端传参
 * @data/创建日期: 2020-05-17 9:58
 **/
@Data
public class GpaInfoDTO {
    String uid;         //学号
    String semester;   //学期
    String gpa;   //当前学期绩点
}
