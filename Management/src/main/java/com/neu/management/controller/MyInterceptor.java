package com.neu.management.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器(拦截controller)
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 进入controller之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 登录拦截 路径修改 session保存登录信息
//        HttpSession session = request.getSession();
//        if(session.getAttribute("admin") != null){
//            return true;
//        }else{
//            try {
//                response.sendRedirect("/admin/login");	//未登录，跳转到登录页
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
        return true;
    }
}
