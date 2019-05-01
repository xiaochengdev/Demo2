package com.demo.service.impl;

import com.demo.dao.impl.FriendDao;
import com.demo.entity.impl.User;
import com.demo.service.interfaces.IBaseService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FriendService implements IBaseService {
    private FriendDao friendDao;

    public void setFriendDao(FriendDao friendDao){
        this.friendDao=friendDao;
    }


    public int add(Object friend,int uid) {
        int count=friendDao.add(friend,uid);
        return count;
    }

    @Override
    public int add(Object friend) {
        int count=friendDao.add(friend);
        return count;
    }

    @Override
    public int delete(int id) {
        int count=friendDao.delete(id);
        return count;
    }

    public int deleteBySql(String sql){
        int count=friendDao.delete(sql);
        return count;
    }

    @Override
    public int update(Object friend) {
        int count=friendDao.update(friend);
        return count;
    }

    @Override
    public List<Object> query() {
        String sql="from Friend";
        List<Object> list=friendDao.query(sql);
        return list;
    }

    @Override
    public List<Object> queryById(String id) {
        String sql="from Friend where fid="+"\""+id+"\"";
        List<Object> list=friendDao.query(sql);
        return list;
    }

    public List<Object> queryByConditions(Map<String,Object> contions) {
        StringBuilder sqlStr=new StringBuilder("from Friend where 1=1");
        int pageNum= (int) contions.get("pageNum");
        int pNum=(int) contions.get("pNum");
        Iterator<Map.Entry<String, Object>> iterator=contions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry=iterator.next();
            String key=entry.getKey();
            Object value=entry.getValue();
            if (key!="pageNum" && key!="pNum") {
                if (value instanceof String) {
                    sqlStr.append(" and " + key + "=" + "'" + value + "'");
                } else {
                    sqlStr.append(" and " + key + "=");
                    sqlStr.append(value);
                }
            }
        }
       // sqlStr.append(" limit "+(pageNum-1)*pNum+","+pNum);
        String sql=sqlStr.toString();
        List<Object> list=friendDao.query(sql,pageNum,pNum);
        return list;
    }

    public int queryCountByConditions(Map<String,Object> contions) {
        StringBuilder sqlStr=new StringBuilder("select count(*) from Friend where 1=1");
        Iterator<Map.Entry<String, Object>> iterator=contions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry=iterator.next();
            String key=entry.getKey();
            Object value=entry.getValue();
            if (key!="pageNum" && key!="pNum") {
                if (value instanceof String) {
                    sqlStr.append(" and " + key + "=" + "'" + value + "'");
                } else {
                    sqlStr.append(" and " + key + "=");
                    sqlStr.append(value);
                }
            }
        }
        // sqlStr.append(" limit "+(pageNum-1)*pNum+","+pNum);
        String sql=sqlStr.toString();
        int count=0;
        count=friendDao.queryCount(sql);
        return count;
    }
}
