package com.guohe.app.exception;

/**
 * 这是里负责question方面的错误枚举
 * 为了防止类爆炸,建议把枚举写成一个接口
 * 每个需要错误枚举的层面自己定义枚举继承该接口
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    //其实这个枚举相当于一个方法,它就是下面的CustomizeErrorCode方法,调用该枚举就会把后面括号的东西作为方法参数来执行该方法
    //也就是把本类的message成员变成括号里的东西
    SYSTEM_ERROR(1000, "其他错误"),
    INFO_LOST(2000, "前端传来的数据缺失"),
    JSON_WRONG(2000, "前端传参不规范"),
    STU_ID_WRONG(2000,"学生学号出错"),
    SIGN_ID_WRONG(2000, "签到验证码错误"),
    LOCATION_WRONG(2000, "位置信息错误"),
    INTERVAL_WRONG(2000, "时间限制错误"),
    CLASSES_WRONG(2000, "班级信息错误"),
    SEMESTER_WRONG(2000, "学年信息错误"),
    SIGN_ID_WRONG2(2000, "该签到名单不包含你,请确认你的验证码是否正确,如果验证码无误,可能是你们的笨比签到发起者选错了需要签到的班级"),
    STATUS_WRONG(2000, "更改的状态码错误,应当是0 1或2"),
    SIGN_TIMEOUT(3000, "签到已过期"),
    HAVE_SIGNED(3000, "已经签到过了"),
    OUT_PLACE(3000, "超出签到范围"),
    SQL_SEARCH_FAIL(4000, "数据库查询失败"),
    SQL_INSERT_FAIL(4000, "数据库插入失败"),
    SQL_UPDATE_FAIL(4000, "数据库更新失败");



    //当然,枚举可以有很多个,每个都是一个传入参数不同但是方法过程相同的东西
    CustomizeErrorCode(String message) {
        this.message = message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    private final String message;
    private int code;

    //重写接口方法
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
