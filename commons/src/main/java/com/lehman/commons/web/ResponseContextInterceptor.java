package com.lehman.commons.web;

/**
 * 用于全局获取,response,
 * Created by Administrator on 2015/9/10.
 */

import com.frame.commons.utils.ActionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseContextInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle( HttpServletRequest request, HttpServletResponse response,
                              Object handler ) throws Exception {
        ActionUtils.setResponse( response );
        return true;

    }
}