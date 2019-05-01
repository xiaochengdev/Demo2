package com.demo.service.interfaces;

import com.demo.entity.impl.User;

import java.util.List;

public interface IBaseService {
    public int add(Object obj);
    public int delete(int id);
    public int update(Object obj);
    public List<Object> query();
    public List<Object> queryById(String id);
}
