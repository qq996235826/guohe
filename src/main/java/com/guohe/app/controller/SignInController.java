package com.guohe.app.controller;

import com.guohe.app.domain.SignInfo;
import com.guohe.app.dto.InitiateSignInDTO;
import com.guohe.app.dto.ResultDTO;
import com.guohe.app.dto.SignInChangeDTO;
import com.guohe.app.dto.SignInInfoDTO;
import com.guohe.app.exception.CustomizeErrorCode;
import com.guohe.app.exception.CustomizeException;
import com.guohe.app.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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


    /**
     * @Description: 前端发起签到的方法
     * @Param: [signInInfo]
     * @Return: com.guohe.app.dto.ResultDTO
     * @Author: Mr.Deng
     */
    @ResponseBody
    @PostMapping("/api/v1/initiate")
    public ResultDTO initiateSignIn(@RequestBody InitiateSignInDTO initiateSignIn) {
        //前端传参缺失
        if (initiateSignIn == null || initiateSignIn.getSemester() == null || initiateSignIn.getCreator() == null || initiateSignIn.getLongitude() == null || initiateSignIn.getLatitude() == null) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        }
        Integer VerificationCode = signInService.createSignIn(initiateSignIn); //创建签到类,返回签到验证码,也是该签到在数据库里的id
        return ResultDTO.okOf(VerificationCode);
    }


    /**
     * @Description: 进行学生签到的controller
     * @Param: [signInInfo]
     * @Return: com.guohe.app.dto.ResultDTO
     * @Author: Mr.Deng
     */
    @ResponseBody
    @PostMapping("/api/v1/signIn")
    public ResultDTO initiateSignIn(@RequestBody SignInInfoDTO signInInfo) {
        //前端传参缺失
        if (signInInfo == null || signInInfo.getStudentId() == null || signInInfo.getSignId() == null) {
            throw new CustomizeException(CustomizeErrorCode.INFO_LOST);
        } else if (Integer.parseInt(signInInfo.getSignId()) <= 0)   //签到验证码也就是签到ID小于或等于0
        {
            throw new CustomizeException(CustomizeErrorCode.SIGN_ID_WRONG);
        }
        return ResultDTO.okOf(signInService.SignIn(signInInfo));    //返回签到是否成功
    }

    /**
     * @Description: 更改签到状态接口
     * @Param: [signInChangeDTO]
     * @Return: com.guohe.app.dto.ResultDTO
     * @Author: Mr.Deng
     */
    @ResponseBody
    @PostMapping("/api/v1/statusChange")
    public ResultDTO changeStatus(@RequestBody SignInChangeDTO signInChangeDTO) {
        //前端传参缺失
        if (signInChangeDTO == null||signInChangeDTO.getSignId()==null||signInChangeDTO.getStatus()==null||signInChangeDTO.getStuId()==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        return ResultDTO.okOf(signInService.changeStatus(signInChangeDTO));    //返回签到是否成功
    }


    /**
     * @Description: 查看你发起了哪些签到.返回map, key是签到的ID, value是签到的创建时间
     * @Param: [id]
     * @Return: com.guohe.app.dto.ResultDTO
     * @Author: Mr.Deng
     */
    @ResponseBody
    @GetMapping("/api/v1/signInHistory")
    public ResultDTO signInHistory(@RequestParam("id") String id) {
        //前端传参缺失
        if (id == null || Objects.equals(id, "")) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        List<SignInfo> signList = signInService.signInHistory(id);
        return ResultDTO.okOf(signList);    //返回签到是否成功
    }

    /**
     * @Description: 获得单个签到信息
     * @Param: [id]
     * @Return: com.guohe.app.dto.ResultDTO
     * @Author: Mr.Deng
     */
    @ResponseBody
    @GetMapping("/api/v1/signInfo")
    public ResultDTO getSignIn(@RequestParam("id") String id) {
        //前端传参缺失
        if (id == null || Objects.equals(id, "")) {
            return ResultDTO.errorOf(CustomizeErrorCode.INFO_LOST);
        }
        return ResultDTO.okOf(signInService.getSignIn(id));    //返回签到是否成功
    }

}
