package com.demo.dao.interfaces;

import com.demo.entity.impl.User;

import java.util.List;

public interface IDao {
    public int add(Object obj);
    public int delete(Object id);
    public int update(Object obj);
    public List<Object> query(String sql);
}
