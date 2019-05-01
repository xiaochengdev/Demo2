package com.demo.action;

import com.demo.Uility.AccountGenerate;
import com.demo.entity.impl.User;
import com.demo.service.impl.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.util.URLDecoderUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport implements BaseAction{

    private UserService userService;
    private User user;
    private Map<String,Object> msg;

    public UserAction(){
        System.out.println("UserAction Init....");
    }
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }

    public String login(){
        msg=new HashMap<>();
        List<Object> list=null;
        Map<String,Object> session=ActionContext.getContext().getSession();
        list=userService.queryById(user.getAccount());
        User resUser=new User();
        if (list!=null&&list.size()>0){
            resUser=(User)list.get(0);
            if((resUser.getPasswd()).equals(user.getPasswd())){
                msg.put("resCode",1);
                msg.put("message","登陆成功");
                if (resUser.getHeadpicture()!=null){
                    resUser.setHeadpicture(URLDecoderUtil.decode(resUser.getHeadpicture(),"UTF-8"));
                }
                msg.put("user",resUser);
                session.put("user",resUser);
                return "login";
            }else {
                msg.put("resCode",-1);
                msg.put("message","密码错误");
                return "error";
            }
        }else {
            msg.put("resCode",-1);
            msg.put("message","用户不存在");
            return "error";
        }
    }

    public String update(){
        msg=new HashMap<>();
        Map<String,Object> session=ActionContext.getContext().getSession();

        int res=userService.update(user);

        if (res!=0){
            msg.put("resCode",1);
            msg.put("message","修改成功");
            return "update";
        }else {
            msg.put("resCode",-1);
            msg.put("message","修改失败");
            return "error";
        }
    }

    public String delete(){
        msg=new HashMap<>();
        Map<String,Object> session=ActionContext.getContext().getSession();

        User user=null;
        user=(User) session.get("user");
        int res=userService.delete(user.getId());

        if (res!=0){
            msg.put("resCode",1);
            msg.put("message","删除成功");
            return "delete";
        }else {
            msg.put("resCode",-1);
            msg.put("message","删除失败");
            return "error";
        }
    }

    public String register(){
        msg=new HashMap<>();
        user.setAccount(AccountGenerate.generateAccount());
        int res=userService.add(user);

        if (res!=0){

            msg.put("resCode",1);
            msg.put("message","注册成功");
            msg.put("account",user.getAccount());
            return "register";
        }else {
            msg.put("resCode",-1);
            msg.put("message","注册失败");
            return "error";
        }
    }

    public String queryUsers(){
        msg=new HashMap<>();
        List<Object> list=null;
        Map<String,Object> session=ActionContext.getContext().getSession();
        Map<String,Object> conditions=new HashMap<>();
        //conditions.put("sex",1);
        //conditions.put("name","xc");
        list=userService.queryByConditions(conditions);
        if (list!=null&&list.size()>0){

                msg.put("resCode",1);
                msg.put("message","有数据");
                msg.put("users",list);
                return "queryusers";

        }else {
            msg.put("resCode",-1);
            msg.put("message","无数据");
            return "error";
        }
    }

    public String getLoginUser(){
        msg=new HashMap<>();
        Map<String,Object> session=ActionContext.getContext().getSession();

        User user=null;
        user=(User) session.getOrDefault("user",null);
        if (user!=null){
            if (user.getHeadpicture()!=null){
                user.setHeadpicture(URLDecoderUtil.decode(user.getHeadpicture(),"UTF-8"));
            }
            msg.put("resCode",1);
            msg.put("user",user);
            return "success";
        }else {
            msg.put("resCode",-1);
            msg.put("user","null");
            return "error";
        }
    }

    public String logout(){
        Map<String,Object> session=ActionContext.getContext().getSession();
        msg=new HashMap<>();
        session.put("user",null);
        msg.put("resCode",1);
        msg.put("message","退出成功");
        return "logout";
    }



}
