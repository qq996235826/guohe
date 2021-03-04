package com.guohe.app.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuoheService
{

    public List login(String username, String password)
    {
        List<Map> infoList=new ArrayList<>();
        Map<String,Object> info=new HashMap();
        info.put("academy","计算机学院");
        info.put("birthday"," 20000121");
        info.put("class_num","1822107111");
        info.put("identity_card_number"," 230505200001210818");
        info.put("major","计算机科学与技术(嵌入式培养)");
        info.put("name"," 韩政原");
        info.put("sex"," 男");
        infoList.add(info);
        return infoList;
    }

    public Map calendar(String username, String password)
    {
        Map<String,Object> map=new HashMap();
        List<String> yearList=new ArrayList<>();
        yearList.add("2018-2019-1");
        yearList.add("2018-2019-2");
        yearList.add("2019-2020-1");
        map.put("allYear",yearList);
        map.put("today","2019-08-17");
        map.put("week","星期六");
        map.put("weekNum",20);
        return map;
    }
}
