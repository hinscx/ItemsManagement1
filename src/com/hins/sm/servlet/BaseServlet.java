package com.hins.sm.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;


/**
 * 重写了HttpServlet的service()方法
 * 用做StaffServlet的父类
 * 重写后的service()中利用反射实现了根据不同的请求调用不同的处理方法
 */
public class BaseServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getMethod().equalsIgnoreCase("get")) {

            /**
             * Tomcat8之后已经默认使用UTF8处理浏览器的请求(URL,Cookies,parameters)
             * 如果浏览器和数据库编码均为UTF8
             * 那么不需要对connector传过来的HttpServletRequest再次包装.
             */

//            if(!(request instanceof RequestUtils)) {
//
//                //request = new RequestUtils(request);//处理get请求编码
//
//            }

        } else {
            request.setCharacterEncoding("utf-8");//处理post请求编码
        }
        response.setContentType("text/html;charset=UTF-8");//处理响应编码

        /**
         * 1. 获取method参数，它是用户想调用的方法 2. 把方法名称变成Method类的实例对象 3. 通过invoke()来调用这个方法
         */
        String methodName = request.getParameter("method");
        Method method = null;
        /**
         * 2. 通过方法名称获取Method对象
         */
        try {
            method = this.getClass().getMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("您要调用的方法：" + methodName + "它不存在！", e);
        }

        /**
         * 3. 通过method对象来调用它
         */
        try {
            String result = (String)method.invoke(this, request, response);
            if(result != null && !result.trim().isEmpty()) {//如果请求处理方法返回不为空
                int index = result.indexOf(":");//获取第一个冒号的位置
                if(index == -1) {//如果没有冒号，使用转发
                    request.getRequestDispatcher(result).forward(request, response);
                } else {//如果存在冒号
                    String start = result.substring(0, index);//分割出前缀
                    String path = result.substring(index + 1);//分割出路径
                    if(start.equals("f")) {//前缀为f表示转发
                        request.getRequestDispatcher(path).forward(request, response);
                    } else if(start.equals("r")) {//前缀为r表示重定向
                        response.sendRedirect(request.getContextPath() + path);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

