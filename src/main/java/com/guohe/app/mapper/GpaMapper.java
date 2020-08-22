package com.guohe.app.mapper;

import com.guohe.app.model.Gpa;
import com.guohe.app.model.GpaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GpaMapper {
    long countByExample(GpaExample example);

    int deleteByExample(GpaExample example);

    int deleteByPrimaryKey(String uid);

    int insert(Gpa record);

    int insertSelective(Gpa record);

    List<Gpa> selectByExampleWithRowbounds(GpaExample example, RowBounds rowBounds);

    List<Gpa> selectByExample(GpaExample example);

    Gpa selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") Gpa record, @Param("example") GpaExample example);

    int updateByExample(@Param("record") Gpa record, @Param("example") GpaExample example);

    int updateByPrimaryKeySelective(Gpa record);

    int updateByPrimaryKey(Gpa record);
}