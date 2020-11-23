package com.sst.handler;

import com.sst.commonutils.MyException;
import com.sst.commonutils.NotBlank;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class CheckParamsInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<String> paramsName = getParamsName((HandlerMethod)handler);
        for(String s : paramsName){
            String parameter = request.getParameter(s);
            if(StringUtils.isEmpty(parameter)){
                throw new MyException("参数"+s+"不能为空");
            }
        }
        return true;
    }

    private List<String> getParamsName(HandlerMethod handlerMethod) {
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        List<String> list = new ArrayList<>();
        for(Parameter parameter : parameters){
            if(parameter.isAnnotationPresent(NotBlank.class)){
                list.add(parameter.getName());
            }
        }
        return list;
    }
}
