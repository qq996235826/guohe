package com.guohe.app.dto;

import lombok.Data;

/**
 * @program: guo_he
 * @description: 用于接受学生签到信息的DTO
 * @author: Mr.Deng
 * @create: 2020-08-11 15:16
 **/
@Data
public class SignInInfoDTO {
    String studentId;   //签到学生的学号
    String signId;      //签到类的ID,也就是验证码
    String longitude;     //经度
    String latitude;     //纬度
}
