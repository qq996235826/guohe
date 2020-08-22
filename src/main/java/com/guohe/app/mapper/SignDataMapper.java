package com.guohe.app.mapper;

import com.guohe.app.model.SignData;
import com.guohe.app.model.SignDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SignDataMapper {
    long countByExample(SignDataExample example);

    int deleteByExample(SignDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SignData record);

    int insertSelective(SignData record);

    List<SignData> selectByExampleWithRowbounds(SignDataExample example, RowBounds rowBounds);

    List<SignData> selectByExample(SignDataExample example);

    SignData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SignData record, @Param("example") SignDataExample example);

    int updateByExample(@Param("record") SignData record, @Param("example") SignDataExample example);

    int updateByPrimaryKeySelective(SignData record);

    int updateByPrimaryKey(SignData record);
}