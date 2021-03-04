package com.guohe.app.controller;

import com.guohe.app.dto.ResultDTO;
import com.guohe.app.service.GuoheService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1")
public class GuoheController
{
    @Resource
    private GuoheService guoheService;

    /***
     * @description/描述: 登录接口
     * @param/参数: [info]
     * @return/返回: com.guohe.app.dto.ResultDTO
     **/
    @RequestMapping("/stu/login")
    public ResultDTO login(String username, String password)//查询需要的信息
    {
        return ResultDTO.okOf(guoheService.login(username, password));
    }


    @RequestMapping("/stu/school/calendar")
    public ResultDTO calendar(@RequestParam("username") String username,@RequestParam("password") String password)//查询需要的信息
    {
        return ResultDTO.okOf(guoheService.calendar(username, password));
    }


}
