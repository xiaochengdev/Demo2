package com.demo.interceptors;

import com.demo.action.BaseAction;
import com.demo.action.UserAction;
import com.demo.entity.impl.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements Interceptor {
    @Override
    public void destroy() {
        System.out.println("LoginInterceptor Destory ...............");
    }

    @Override
    public void init() {
        System.out.println("LoginInterceptor Init ...............");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext actionContext=actionInvocation.getInvocationContext();
        BaseAction obj= (BaseAction) actionInvocation.getAction();
        Map<String,Object> message=new HashMap<>();
        String actionName=actionInvocation.getProxy().getActionName();
        String actionMethod=actionInvocation.getProxy().getMethod();
        Map<String,Object> session=actionContext.getSession();
        User user= (User) session.getOrDefault("user",null);
        if (user==null){
            if (actionName.startsWith("user")){
                if (actionMethod.endsWith("login")||actionMethod.endsWith("register")||actionMethod.endsWith("test")){
                    return actionInvocation.invoke();
                }else {
                    message.put("resCode",-1);
                    message.put("message","请先登陆");
                    obj.setMsg(message);
                    return "error";
                }
            }else {
                message.put("resCode",-1);
                message.put("message","请先登陆");
                obj.setMsg(message);
                return "error";
            }
        }else {
            return actionInvocation.invoke();
        }
    }
}
