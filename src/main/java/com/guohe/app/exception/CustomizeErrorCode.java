package com.guohe.app.exception;

//这是里负责question方面的错误枚举,为了防止类爆炸,建议把枚举写成一个接口,每个需要错误枚举的层面自己定义枚举继承该接口
public enum CustomizeErrorCode implements ICustomizeErrorCode
{
    //其实这个枚举相当于一个方法,它就是下面的CustomizeErrorCode方法,调用该枚举就会把后面括号的东西作为方法参数来执行该方法
    //也就是把本类的message成员变成括号里的东西
    SYSTEM_ERROR(100,"其他错误"),
    RANK_INFO_LOST(2000,"前端传来的用于查询的数据缺失"),
    JSON_WRONG(2001,"前端传参不规范"),
    SQL_SEARCH_FAIL(3000,"数据库查询失败");
    //当然,枚举可以有很多个,每个都是一个传入参数不同但是方法过程相同的东西



    CustomizeErrorCode(String message)
    {
        this.message = message;
    }

    CustomizeErrorCode(Integer code, String message)
    {
        this.message = message;
        this.code = code;
    }

    private String message;
    private Integer code;

    //重写接口方法
    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public Integer getCode()
    {
        return code;
    }


}
