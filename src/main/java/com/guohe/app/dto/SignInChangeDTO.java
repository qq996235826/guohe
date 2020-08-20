package com.guohe.app.dto;

import lombok.Data;

/**
 * @program: guo_he
 * @description: 用于管理者改变签到状态的前端传参
 * @author: Mr.Deng
 * @create: 2020-08-19 00:12
 **/
@Data
public class SignInChangeDTO {
    String signId;
    String stuId;
    String status;      //状态代码,0迟到,1成功,3旷课
}
