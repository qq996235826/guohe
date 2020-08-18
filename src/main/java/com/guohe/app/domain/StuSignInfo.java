package com.guohe.app.domain;

import lombok.Data;

/**
 * @program: guo_he
 * @description: 学生签到信息
 * @author: Mr.Deng
 * @create: 2020-08-18 22:43
 **/
@Data
public class StuSignInfo {
    String name;    //姓名
    String uid;     //学号
    String status;      //签到状态

    public StuSignInfo(String name, String uid, String status) {
        this.name = name;
        this.uid = uid;
        this.status = status;
    }
}
