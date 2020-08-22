package com.guohe.app.controller;

import com.guohe.app.dto.InitiateSignInDTO;
import com.guohe.app.dto.ResultDTO;
import com.guohe.app.dto.SignInChangeDTO;
import com.guohe.app.dto.SignInInfoDTO;
import com.guohe.app.service.SignInService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: guo_he
 * @description: 负责上课签到功能的controller
 * @author: Mr.Deng
 * @create: 2020-08-09 18:52
 **/
@RequestMapping("/api/v1")
public class SignInController {

    @Resource
    private SignInService signInService;

    /**
     * 前端发起签到的方法
     * @param initiateSignIn
     * @return
     */
    @PostMapping("/initiate")
    public ResultDTO initiateSignIn(@RequestBody InitiateSignInDTO initiateSignIn) {
        return ResultDTO.okOf(signInService.createSignIn(initiateSignIn));//创建签到类,返回签到验证码,也是该签到在数据库里的id
    }

    /**
     * 进行学生签到的controller
     *
     * @param signInInfo
     * @return
     */
    @PostMapping("/signIn")
    public ResultDTO initiateSignIn(@RequestBody SignInInfoDTO signInInfo) {
        return ResultDTO.okOf(signInService.doSignIn(signInInfo));    //返回签到是否成功
    }

    /**
     * 更改签到状态接口
     *
     * @param signInChangeDTO
     * @return
     */
    @PostMapping("/statusChange")
    public ResultDTO changeStatus(@RequestBody SignInChangeDTO signInChangeDTO) {
        return ResultDTO.okOf(signInService.changeStatus(signInChangeDTO));    //返回更改是否成功
    }

    /**
     * 查看你发起了哪些签到
     *
     * @param id
     * @return
     */
    @GetMapping("/signInHistory")
    public ResultDTO signInHistory(@RequestParam("id") String id) {
        return ResultDTO.okOf(signInService.signInHistory(id));    //返回签到是否成功
    }

    /**
     * 获得单个签到信息
     *
     * @param id
     * @return
     */
    @GetMapping("/signInfo")
    public ResultDTO getSignIn(@RequestParam("id") String id) {
        return ResultDTO.okOf(signInService.getSignIn(id));    //返回签到是否成功
    }
}
