package com.demo.service.impl;

import com.demo.dao.impl.UserDao;
import com.demo.entity.impl.User;
import com.demo.service.interfaces.IBaseService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserService implements IBaseService {
    private UserDao userDao;

    public UserService(){
        System.out.println("userService init........");
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int add(Object user) {
        int count=userDao.add(user);
        return count;
    }

    @Override
    public int delete(int id) {
        int count=userDao.delete(id);
        return count;
    }

    @Override
    public int update(Object user) {
        int count=userDao.update(user);
        return count;
    }

    @Override
    public List<Object> query() {
        String sql="select * from users";
        List<Object> list=userDao.query(sql);
        return list;
    }

    @Override
    public List<Object> queryById(String account) {
        //dao.queryByUser(account);
        String sql="from User where account="+"\'"+account+"\'";
        List<Object> list=userDao.query(sql);
        return list;
    }

    public List<Object> queryByConditions(Map<String,Object> contions) {
        StringBuilder sqlStr=new StringBuilder("select * from user where 1=1");
        Iterator<Map.Entry<String, Object>> iterator=contions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry=iterator.next();
            String key=entry.getKey();
            Object value=entry.setValue(key);
            if(value instanceof String){
                sqlStr.append(" and "+key+"="+"\""+value+"\"");
            }else {
                sqlStr.append(" and " + key + "=");
                sqlStr.append(value);
            }
        }

        String sql=sqlStr.toString();
        List<Object> list=userDao.query(sql);
        return list;
    }
}
