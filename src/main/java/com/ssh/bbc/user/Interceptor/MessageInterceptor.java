package com.ssh.bbc.user.Interceptor;

import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.util.BussinessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 版主拦截器
 */
public class MessageInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(MessageInterceptor.class);
    //在Controller处理之前调用, 返回false时整个请求结束   拦截请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        Tuser admin = (Tuser)request.getSession().getAttribute("user");
        logger.info("链接"+requestURI+"进入拦截器");
        if(admin!=null && admin.getIsOwner() == 1 ) {
            return true;
        }
        if(admin!=null && admin.getIsOwner() == 2 ) {
            return true;
        }
        BussinessUtil.pleaseLogin("权限不足,请重新登录");
        return false;
    }
    //在Controller调用之后执行, 但它会在DispatcherServlet进行视图的渲染之前执行, 也就是说在这个方法中你可以对ModelAndView进行操作   拦截响应
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //afterCompletion该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
