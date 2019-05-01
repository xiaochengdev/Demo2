package com.demo.action;

import com.demo.Uility.AccountGenerate;
import com.demo.Uility.DataUiltity;
import com.demo.dao.impl.UserDao;
import com.demo.entity.impl.User;
import com.demo.service.impl.UserService;
import sun.rmi.runtime.Log;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hello {
    public String execute(){
        /*UserDao dao=new UserDao();
        dao.query();*/
        UserService service=new UserService();
        User user=new User();
        //user.setAccount(AccountGenerate.generateAccount());
        user.setId(2);
        user.setPasswd("123456");
        user.setSex("1");
        user.setName("joke");
        user.setDes("movie");
        //int res=service.add(user);
        //int res=service.update(user);
        int res=service.delete(user.getId());
        if (res>0){
            System.out.println("注册成功");
        }else {
            System.out.println("注册失败");
        }
        System.out.println("hello");
        return "success";
    }
}
