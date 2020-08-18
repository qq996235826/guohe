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
}
