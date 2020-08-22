package com.guohe.app.mapper;

import com.guohe.app.model.Score;
import com.guohe.app.model.ScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ScoreMapper {
    long countByExample(ScoreExample example);

    int deleteByExample(ScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    List<Score> selectByExampleWithRowbounds(ScoreExample example, RowBounds rowBounds);

    List<Score> selectByExample(ScoreExample example);

    Score selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByExample(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}