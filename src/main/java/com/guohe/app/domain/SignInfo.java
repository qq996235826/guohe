package com.guohe.app.domain;

import com.guohe.app.model.SignIn;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: guo_he
 * @description: 签到的信息
 * @author: Mr.Deng
 * @create: 2020-08-19 23:40
 **/
@Data
public class SignInfo {
    private Integer signInId;

    private String createTime;

    private Integer isOverTime;

    private String creator;

    private Double longitude;

    private Double latitude;

    private String year;

    private Integer timeLimit;

    private String name;

    private String classes;

    public SignInfo(SignIn signIn) {
        this.signInId = signIn.getSignInId();
        this.isOverTime = signIn.getIsOverTime();
        this.creator = signIn.getCreator();
        this.longitude = signIn.getLongitude();
        this.latitude = signIn.getLatitude();
        this.year = signIn.getYear();
        this.timeLimit = signIn.getTimeLimit();
        this.name = signIn.getName();
        this.classes = signIn.getClasses();
        Date date = new Date();
        date.setTime(signIn.getCreateTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = simpleDateFormat.format(date);
    }
}
