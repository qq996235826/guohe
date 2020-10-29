package com.guohe.app.controller;

import com.guohe.app.dto.ResultDTO;
import com.guohe.app.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author deng
 * @version 1.0
 * @description 负责统计数据的Controller
 * @date 2020/10/10 22:59
 */
@CrossOrigin//跨域注解
@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @PostMapping("/statistics")
    public ResultDTO getStatisticsData()
    {
        return statisticsService.getProportion();
    }

}
