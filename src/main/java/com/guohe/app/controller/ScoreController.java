package com.guohe.app.controller;

import com.guohe.app.dto.GpaInfoDTO;
import com.guohe.app.dto.ResultDTO;
import com.guohe.app.dto.ScoreInfoDTO;
import com.guohe.app.exception.CustomizeErrorCode;
import com.guohe.app.exception.CustomizeException;
import com.guohe.app.service.RankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @version/版本: V1.0
 * @author/作者: 邓浩然
 * @description/描述: 负责查询排名的controller
 * @data/创建日期: 2020-05-16 21:08
 **/
@RequestMapping("/api/v1")
public class ScoreController {
    @Resource
    private RankService rankService;

    /***
     * @description/描述:查单科全校排名
     * @param/参数: [info]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    @PostMapping("/api/v1/scoreRank")
    public ResultDTO searchRank(@RequestBody ScoreInfoDTO info)//查询需要的信息
    {
        return rankService.getRank(info);
    }

    /**
     * @description/描述:查学院内绩点排名
     * @param/参数: [info]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    @PostMapping("/api/v1/gpaRank")
    public ResultDTO searchGpaRank(@RequestBody GpaInfoDTO info)//查询需要的信息
    {
        return rankService.getGpaRank(info);
    }

    /**
     * @description/描述:查询所有有分数的科目的排名,考试考查排名分开
     * @param/参数: [info]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    @PostMapping("/api/v1/rank")
    public ResultDTO getAllRank(@RequestBody GpaInfoDTO info)
    {
        return rankService.getAllRank(info);
    }
}
