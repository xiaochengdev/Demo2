package com.demo.dao.impl;

import com.demo.Uility.DataUiltity;
import com.demo.dao.interfaces.IDao;
import com.demo.entity.impl.Comment;
import com.demo.entity.impl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentDao implements IDao {
    private Connection con=null;
    public CommentDao(){
        con=DataUiltity.getConnection();
    }
    @Override
    public int add(Object obj) {
        Comment comment= (Comment) obj;
        String sql="";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
