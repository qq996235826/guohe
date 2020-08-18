package com.guohe.app.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: guo_he
 * @description: 负责创建签到的前端发来的DTO
 * @author: Mr.Deng
 * @create: 2020-08-11 16:27
 **/
@Data
public class InitiateSignInDTO {
    String semester;    //学年
    String creator;   //发起人ID
    String longitude;     //经度
    String latitude;     //纬度
    String name;        //签到的说明
    Integer interval;  //签到期限,单位是分钟
    List<String> classes;   //需要签到的班级号
}
