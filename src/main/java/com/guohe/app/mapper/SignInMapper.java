package com.guohe.app.mapper;

import com.guohe.app.model.SignIn;
import com.guohe.app.model.SignInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SignInMapper {
    long countByExample(SignInExample example);

    int deleteByExample(SignInExample example);

    int deleteByPrimaryKey(Integer signInId);

    int insert(SignIn record);

    int insertSelective(SignIn record);

    List<SignIn> selectByExampleWithRowbounds(SignInExample example, RowBounds rowBounds);

    List<SignIn> selectByExample(SignInExample example);

    SignIn selectByPrimaryKey(Integer signInId);

    int updateByExampleSelective(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByExample(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByPrimaryKeySelective(SignIn record);

    int updateByPrimaryKey(SignIn record);
}