package com.guohe.app.mapper;

import com.guohe.app.model.SignIn;

/**
 * @program: guo_he
 * @description: 自定义签到mapper
 * @author: Mr.Wang
 * @create: 2020-08-13 00:30
 **/
public interface SignInExtMapper {
    int insertAndGetKey(SignIn signIn);
}
