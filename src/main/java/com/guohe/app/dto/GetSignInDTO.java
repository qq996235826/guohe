package com.guohe.app.dto;

import com.guohe.app.domain.StuSignInfo;
import com.guohe.app.model.SignIn;
import lombok.Data;

import java.util.ArrayList;

/**
 * @program: guo_he
 * @description: 用于返回给前端单个签到详细信息的DTO, 包含两个列表, 分别放置已签到的和未签到的人员名单
 * @author: Mr.Deng
 * @create: 2020-08-18 19:37
 **/
@Data
public class GetSignInDTO {
    SignIn signIn;
    ArrayList<StuSignInfo> successList;
    ArrayList<StuSignInfo> failList;

    public GetSignInDTO(SignIn signIn, ArrayList<StuSignInfo> successList, ArrayList<StuSignInfo> failList) {
        this.signIn = signIn;
        this.successList = successList;
        this.failList = failList;
    }
}
