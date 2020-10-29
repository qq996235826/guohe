package com.guohe.app.service;

import com.guohe.app.mapper.MarqueeMessageMapper;
import com.guohe.app.model.MarqueeMessage;
import com.guohe.app.model.MarqueeMessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deng
 * @version 1.0
 * @description 负责提供消息的service
 * @date 2020/10/29 23:20
 */
@Service
public class MessageService {
    @Autowired
    MarqueeMessageMapper marqueeMessageMapper;

    //消息之间的分隔符
    private String f="&";

    public String getMarqueeMessage() {
        //用于存放搜索到的结果
        List<MarqueeMessage> messageArrayList=new ArrayList<>();
        //exmaple用于搜索条件
        MarqueeMessageExample example=new MarqueeMessageExample();
        example.createCriteria().andVisibleEqualTo(true);
        //查询数据库
        messageArrayList=marqueeMessageMapper.selectByExample(example);

        //返回结果
        StringBuilder res= new StringBuilder();
        //如果得到了数据
        if(messageArrayList.size()!=0)
        {

            for(int a=0;a<messageArrayList.size();a++)
            {
                res.append(f).append(messageArrayList.get(a).getMessage());
            }
        }
        else
        {
            return res.toString();
        }
        return res.toString();
    }
}
