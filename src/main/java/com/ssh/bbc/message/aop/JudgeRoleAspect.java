package com.ssh.bbc.message.aop;

import com.ssh.bbc.message.domain.Tmessage;
import com.ssh.bbc.message.service.ImessageService;
import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.util.BussinessUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 进行对帖子操作时判断其是否为对应的版主的切面类
 */
@Aspect
@Component
public class JudgeRoleAspect {

    @Autowired
    private ImessageService imessageService;


    @Pointcut("execution(* com.ssh.bbc.message.controller.OwnerController.*(int,javax.servlet.http.HttpServletRequest))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (!judgeRole((int)args[0],(HttpServletRequest)args[1])){
            BussinessUtil.error("操作不在你的权限之内");
        }
    }

    public boolean judgeRole(int id, HttpServletRequest request){
        HttpSession session = request.getSession();
        Tuser user = (Tuser)session.getAttribute("user");
        if (user.getIsOwner() == 2){
            return true;
        }else {
            Tmessage tmessage = imessageService.queryMessageById(id);
            return tmessage.getMessKind().equals(session.getAttribute("role"));
        }
    }
}
