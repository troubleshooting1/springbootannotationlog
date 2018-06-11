package com.anji.springbootannotationlog.aspect;

import com.anji.springbootannotationlog.annotation.SystemControllerLog;
import com.anji.springbootannotationlog.model.ExecutionResult;
import com.anji.springbootannotationlog.model.SystemLog;
import com.anji.springbootannotationlog.service.SystemLogService;
import com.anji.springbootannotationlog.util.ReturnCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:09
 */
@Aspect
@Component
@Order(-5)
public class SystemLogAspect {
    @Autowired
    private SystemLogService systemLogService;

    //定义service切入点拦截规则，拦截SystemServiceLog注解的方法
    @Pointcut("@annotation(com.anji.springbootannotationlog.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }


    //定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
    @Pointcut("@annotation(com.anji.springbootannotationlog.annotation.SystemControllerLog)")
    public void controllerAspect(){}

    /**
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public ExecutionResult recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        SystemLog systemLog = new SystemLog();
        Object proceed = null;

        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().getAttribute("user");
        systemLog.setUserid("conquer");

        //获取请求的ip
        String ip = request.getRemoteAddr();
        systemLog.setRequestip(ip);

        //获取执行的方法名
        systemLog.setActionmethod(joinPoint.getSignature().getName());

        //获取方法的执行时间
        Date date = new Date();
        systemLog.setActiondate(date);

        proceed = joinPoint.proceed();
        ExecutionResult result = (ExecutionResult) proceed;

        if (result.getResultCode().equals(ReturnCode.RES_SUCCESS)) {
            systemLog.setType("1");
            systemLog.setDescription(getControllerMethodDescription(joinPoint) + ":" + result.getMsg());
        } else {
            systemLog.setType("2");
            systemLog.setExceptioncode(result.getMsg());
        }

        Object[] params = joinPoint.getArgs();
        String returnStr = "";
        for (Object param : params) {
            if (param instanceof String) {
                returnStr += param;
            } else if (param instanceof Integer) {
                returnStr+=param;
            }
        }

        systemLog.setParams(returnStr);

        systemLogService.saveUser(systemLog);

        return result;
    }

    /**
     * 获取controller的操作信息
     * @param point
     * @return
     * @throws Exception
     */
    public String getControllerMethodDescription(ProceedingJoinPoint point) throws Exception{
        String targetName=point.getTarget().getClass().getName();       //获取连接点目标类名
        String methodName=point.getSignature().getName();               //获取连接点签名的方法名
        Object[] args=point.getArgs();                                  //获取连接点参数
        Class targetClass=Class.forName(targetName);                    //根据连接点类的名字获取指定类
        Method[] methods=targetClass.getMethods();

        String description="";

        for(Method method:methods){
            if(method.getName().equals(methodName)){
                Class[] clazzs=method.getParameterTypes();
                if(clazzs.length==args.length){
                    description=method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }

        return description;
    }

    @AfterThrowing(pointcut = "controllerAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e) throws Throwable{
        SystemLog systemLog=new SystemLog();
        Object proceed=null;

        HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().getAttribute("user");
        systemLog.setUserid("conquer");

        String ip=request.getRemoteAddr();
        systemLog.setRequestip(ip);
        systemLog.setType("2");
        systemLog.setExceptioncode(e.getClass().getName());
        systemLog.setExceptiondetail(e.getMessage());
        systemLogService.saveUser(systemLog);
    }
}
