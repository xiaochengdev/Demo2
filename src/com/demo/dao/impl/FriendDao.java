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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDao implements IDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int add(Object obj, int uid) {

        Friend friend= (Friend) obj;
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        try {
            Serializable id=session.save(friend);
            tx.commit();
            session.close();
            return id.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int add(Object obj) {
        Friend friend= (Friend) obj;
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        try {
            Serializable id=session.save(friend);
            tx.commit();
            session.close();
            return id.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int delete(Object obj) {

        Friend friend= (Friend) obj;
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        try {
            session.delete(obj);
            tx.commit();
            session.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return 0;
        }
    }

    public int delete(String sql) {
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        try {
            Query query=session.createQuery(sql);
            int count=query.executeUpdate();
            tx.commit();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int update(Object obj) {
        Session session=sessionFactory.openSession();
        Friend friend= (Friend) obj;
       /* String sql="update friend set name=?,sex=?,birthday=?,mobile=?,email=?,address=?" +
                "where fid=?";*/
        List<Friend> list=new ArrayList<>();
        try {
           /* PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,friend.getName());
            ps.setInt(2,friend.getSex());
            ps.setDate(3,(Date)friend.getBirthday());
            ps.setString(4,friend.getMobile());
            ps.setString(5,friend.getEmail());
            ps.setString(6,friend.getAddress());
            ps.setInt(7,friend.getFid());*/
           session.update(friend);
            session.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            list=null;
            session.close();
            return 0;
        }
    }

    @Override
    public List<Object> query(String sql) {
        return null;
    }


    public List<Object> query(String sql,int pageNum,int pNum) {
        List<Object> list=new ArrayList<>();
        Session session=sessionFactory.openSession();
        try {
            Query query=session.createQuery(sql);
            query.setFirstResult((pageNum-1)*pNum);
            query.setMaxResults(pNum);
            List<Friend> friends =query.list();
            for (int i=0;i<friends.size();i++){
                list.add(friends.get(i));
            }
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    public int  queryCount(String sql) {
        Session session=sessionFactory.openSession();
        int count=0;
        try {
            Query query=session.createQuery(sql);
            count= ((Number) query.uniqueResult()).intValue();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return count;
        }
    }
}
