package com.guohe.app.controller;

import com.guohe.app.domain.SignInfo;
import com.guohe.app.dto.*;
import com.guohe.app.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: guo_he
 * @description: 负责上课签到功能的controller
 * @author: Mr.Deng
 * @create: 2020-08-09 18:52
 **/
@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;


    //前端发起签到的方法
    @ResponseBody
    @PostMapping("/api/v1/initiate")
    public ResultDTO<Integer> initiateSignIn(@RequestBody InitiateSignInDTO initiateSignIn) {
        return ResultDTO.okOf(signInService.createSignIn(initiateSignIn));//创建签到类,返回签到验证码,也是该签到在数据库里的id
    }


    //进行学生签到的controller
    @ResponseBody
    @PostMapping("/api/v1/signIn")
    public ResultDTO<String> initiateSignIn(@RequestBody SignInInfoDTO signInInfo) {
        return ResultDTO.okOf(signInService.SignIn(signInInfo));    //返回签到是否成功
    }

    //更改签到状态接口
    @ResponseBody
    @PostMapping("/api/v1/statusChange")
    public ResultDTO<String> changeStatus(@RequestBody SignInChangeDTO signInChangeDTO) {
        return ResultDTO.okOf(signInService.changeStatus(signInChangeDTO));    //返回更改是否成功
    }


    //查看你发起了哪些签到
    @ResponseBody
    @GetMapping("/api/v1/signInHistory")
    public ResultDTO<List<SignInfo>> signInHistory(@RequestParam("id") String id) {
        return ResultDTO.okOf(signInService.signInHistory(id));    //返回签到是否成功
    }

    //获得单个签到信息
    @ResponseBody
    @GetMapping("/api/v1/signInfo")
    public ResultDTO<GetSignInDTO> getSignIn(@RequestParam("id") String id) {
        return ResultDTO.okOf(signInService.getSignIn(id));    //返回签到是否成功
    }

}
