package com.guohe.app.controller;

import com.guohe.app.dto.ResultDTO;
import com.guohe.app.service.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author deng
 * @version 1.0
 * @description 负责给果核app提供各种消息的controller
 * @date 2020/10/29 23:19
 */
@CrossOrigin//跨域注解
@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Resource
    private MessageService messageService;

    @GetMapping("/getMarqueeMessage")
    public ResultDTO getMarqueeMessage()
    {
        return ResultDTO.okOf(messageService.getMarqueeMessage());
    }
}
