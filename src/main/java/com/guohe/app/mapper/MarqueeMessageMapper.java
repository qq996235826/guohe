package com.guohe.app.mapper;

import com.guohe.app.model.MarqueeMessage;
import com.guohe.app.model.MarqueeMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MarqueeMessageMapper {
    long countByExample(MarqueeMessageExample example);

    int deleteByExample(MarqueeMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarqueeMessage record);

    int insertSelective(MarqueeMessage record);

    List<MarqueeMessage> selectByExampleWithRowbounds(MarqueeMessageExample example, RowBounds rowBounds);

    List<MarqueeMessage> selectByExample(MarqueeMessageExample example);

    MarqueeMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarqueeMessage record, @Param("example") MarqueeMessageExample example);

    int updateByExample(@Param("record") MarqueeMessage record, @Param("example") MarqueeMessageExample example);

    int updateByPrimaryKeySelective(MarqueeMessage record);

    int updateByPrimaryKey(MarqueeMessage record);
}