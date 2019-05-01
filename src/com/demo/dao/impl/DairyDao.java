package com.demo.dao.impl;

import com.demo.Uility.DataUiltity;
import com.demo.dao.interfaces.IDao;
import com.demo.entity.impl.Dairy;
import com.demo.entity.impl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DairyDao implements IDao {
    private Connection con=null;
    public DairyDao(){
        con=DataUiltity.getConnection();
    }
    @Override
    public int add(Object obj) {
        Dairy dairy= (Dairy) obj;
        int res=0;
        String sql="insert into dairy(uid,content,createDate,state,stateDate) values (?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,dairy.getUid());
            ps.setString(2,dairy.getContent());
            long times=new Date().getTime();
            java.sql.Date createDate=new java.sql.Date(times);
            ps.setDate(3,createDate);
            ps.setInt(4,dairy.getState());
            ps.setDate(5,createDate);
            boolean result=ps.execute();
            if (!result){
                res=ps.getUpdateCount();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(Object id) {
        return 0;
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public List<Object> query(String sql) {
        return null;
    }
}
