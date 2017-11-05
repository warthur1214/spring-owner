package com.warthur.demo.test.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        testReadFile();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

    private void testReadFile() {
        URL url = this.getClass().getClassLoader().getResource("/README.txt");

        InputStream inputStream;
        BufferedReader br = null;
        StringBuilder contentHolder = new StringBuilder("");
        if (url != null) {
            try {
                inputStream = url.openStream();
                if (inputStream != null) {
                    br = new BufferedReader(new InputStreamReader(inputStream));
                }

                contentHolder = new StringBuilder();
                String lineContent = null;
                if (br != null) {
                    while ((lineContent = br.readLine()) != null) {
                        contentHolder.append(lineContent);
                    }
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("content=" + contentHolder);
    }
}
