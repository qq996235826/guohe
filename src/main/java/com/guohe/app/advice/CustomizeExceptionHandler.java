package com.guohe.app.advice;


import com.guohe.app.dto.ResultDTO;
import com.guohe.app.exception.CustomizeErrorCode;
import com.guohe.app.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//页面出错误处理类
@ControllerAdvice   //错误处理类注解,如果后面不加括号就会扫面所有地方的错误
public class CustomizeExceptionHandler
{
    @ExceptionHandler(Exception.class)  //括号里写你要处理的错误的class字节码
    @ResponseBody
    Object handler(HttpServletRequest request, Throwable e, Model model)
    {//Throwable 是所有错误的父类,它负责接收抛出的错误

        //contentType是前端返回的数据类型,这里我们根据ContentType来进行分类错误处理
        String contentType = request.getContentType();

        if ("application/json".equals(contentType))//如果前端返回的contentType是json格式,也就是是内容方面的
        {
            //返回json
            if (e instanceof CustomizeException)    //instanceof用于判断一个对象是否是是一个类的实例,这里是判断收到的错误e是不是CustomizeException类
            {
                //返回错误信息
                e.printStackTrace();
                return ResultDTO.errorOf((CustomizeException) e);
            }
            else
            {
                //其他错误显示的信息
                e.printStackTrace();
                return ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
        }
        else    //前端返回的是其他格式的数据
        {
            //错误页面跳转
            if (e instanceof CustomizeException)    //instanceof用于判断一个对象是否是是一个类的实例,这里是判断收到的错误e是不是CustomizeException类
            {
                //question下错误显示的信息
                e.printStackTrace();
                return ResultDTO.errorOf((CustomizeException) e);
            }
            else
            {
                //其他错误显示的信息
                e.printStackTrace();
                return ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }

            //ModelAndView一般用于处理请求后,不仅要进行跳转,还要在跳转过程中传递数据的情形
//            return new ModelAndView("error");//括号里是错误页面名
        }


    }
}
