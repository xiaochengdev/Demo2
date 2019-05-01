package com.demo.dao.impl;

import com.demo.Uility.DataUiltity;
import com.demo.Uility.HibernateUtils;
import com.demo.dao.interfaces.IDao;
import com.demo.entity.impl.Friend;
import com.demo.entity.impl.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao implements IDao {

    public UserDao(){
        System.out.println("userDao init......");
    }
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(Object obj) {

        User user= (User) obj;
        int count=0;
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        try {
            Serializable serializable = session.save(user);
            count=serializable.hashCode();
            transaction.commit();
            session.close();
            return count;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int delete(Object id) {
        int uid= (int) id;
        int count=0;
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        try {
            User user=session.get(User.class,uid);
            session.delete(user);
            transaction.commit();
            session.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int update(Object obj) {
        User user= (User) obj;
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.update(user);
            transaction.commit();
            session.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return 0;
        }
    }

    public List<User> queryByUser(String  account){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        String hql="from User where account=:account";
        try {
            Query query=session.createQuery(hql);
            query.setParameter("account",account);
            List<User> list=query.list();
            transaction.commit();
            session.close();
            return list;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public List<Object> query(String sql) {
        List<Object> list=new ArrayList<>();
        Session session=sessionFactory.openSession();
        try {
            Query query=session.createQuery(sql);
            list=query.list();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    public List<User> test(User user){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        User user1=session.get(User.class,2);
        User user2=session.get(User.class,2);
        System.out.println(user1==user2);
        transaction.commit();
        session.close();

        Session session2=HibernateUtils.getSession();
        Transaction transaction2=session2.beginTransaction();
        User user3=session2.get(User.class,2);
        System.out.println(user2==user3);
        User user4=session2.get(User.class,2);
        System.out.println(user3==user4);
        transaction2.commit();
        session2.close();

        Session session3=HibernateUtils.getSession();
        Session session4=HibernateUtils.getSession();
        Session session5=HibernateUtils.getSession();
        Session session6=HibernateUtils.getSession();
        Session session7=HibernateUtils.getSession();
        /*Friend friend1=new Friend();
        friend1.setName("测试5");
        friend1.setSex(1);
        Friend friend2=new Friend();
        friend2.setName("测试6");
        friend2.setSex(0);*/

        //friend1.setUser(user);
        //friend2.setUser(user);

       // user.getFriendSet().add(friend1);
        //user.getFriendSet().add(friend2);

        //session.save(friend1);
        //session.save(friend2);

        //session.update(user);
       /* String hql="from User";
        Query query=session.createQuery(hql);
        List<User> res=query.list();
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i).getName());
        }*/

        return null;
    }



}
