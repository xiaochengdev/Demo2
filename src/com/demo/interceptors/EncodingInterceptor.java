package com.demo.interceptors;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

public class EncodingInterceptor implements Interceptor {
    @Override
    public void destroy() {
        System.out.println("EncodingInterceptor Destory ...............");
    }

    @Override
    public void init() {
        System.out.println("EncodingInterceptor Init ...............");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext actionContext=actionInvocation.getInvocationContext();
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response= (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
        if( request.getMethod().compareToIgnoreCase("post")>=0){
            try {
                request.setCharacterEncoding("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            Iterator<String[]> iterval=request.getParameterMap().values().iterator();
            while(iterval.hasNext()){
                String[] parames = iterval.next();
                for (int i = 0; i < parames.length; i++) {
                    try {
                        parames[i]=new String(parames[i].getBytes("utf-8"),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("EncodingInterceptor intercept ...............");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        return actionInvocation.invoke();
    }
}
