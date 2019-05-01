package com.demo.action;

import com.demo.entity.impl.Friend;
import com.demo.entity.impl.User;
import com.demo.service.impl.FriendService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendAction extends ActionSupport implements BaseAction{
    private FriendService service;
    private Friend friend;
    private Map<String,Object> msg;
    private String[] batchId;
    private int pageNum;
    private int pNum;


    public void setFriendService(FriendService friendService){
        this.service=friendService;
    }

    @Override
    public void setMsg(Map<String, Object> msg) {
        this.msg=msg;
    }

    @Override
    public Map<String, Object> getMsg() {
        return this.msg;
    }

    public FriendService getService() {
        return service;
    }

    public void setService(FriendService service) {
        this.service = service;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String[] getBatchId() {
        return batchId;
    }

    public void setBatchId(String[] batchId) {
        this.batchId = batchId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String findFriendByFid(){
        msg=new HashMap<>();
        List<Object> list=null;
        Map<String,Object> session=ActionContext.getContext().getSession();
        list=service.queryById(friend.getFid()+"");
        Friend resFriend=new Friend();
        if (list!=null&&list.size()>0){
            resFriend=(Friend) list.get(0);
            msg.put("resCode",1);
            msg.put("message","查找成功");
            msg.put("friend",resFriend);
            return "success";
        }else {
            msg.put("resCode",-1);
            msg.put("message","不存在");
            return "error";
        }
    }

    public String update(){
        msg=new HashMap<>();
        Map<String,Object> session=ActionContext.getContext().getSession();

        int res=service.update(friend);

        if (res!=0){
            msg.put("resCode",1);
            msg.put("message","修改成功");
            return "success";
        }else {
            msg.put("resCode",-1);
            msg.put("message","修改失败");
            return "error";
        }
    }

    public String delete(){
        msg=new HashMap<>();
        Map<String,Object> session=ActionContext.getContext().getSession();

        Friend friend=null;
        User user=(User) session.get("user");
        //int res=service.delete(friend.getFid());
        StringBuilder sqlStr=new StringBuilder("");
        sqlStr.append("delete from Friend where 1=1 and fid in (-1");
        if (batchId!=null&&batchId.length>0){
            for (int i=0;i<batchId.length;i++){
                sqlStr.append(","+Integer.valueOf(batchId[i]));
            }
        }
        sqlStr.append(")");
        int res=service.deleteBySql(sqlStr.toString());
        if (res!=0){
            msg.put("resCode",1);
            msg.put("message","删除成功");
            return "success";
        }else {
            msg.put("resCode",-1);
            msg.put("message","删除失败");
            return "error";
        }
    }

    public String add(){
        msg=new HashMap<>();
       /* Friend friend=new Friend();
        friend.setName("黎明");
        friend.setSex(1);
        friend.setBirthday(new Date(2018,8,24));
        friend.setMobile("18362826352");
        friend.setEmail("18362826352@163.com");
        friend.setAddress("貴州省安順市");*/
        Map<String,Object> session=ActionContext.getContext().getSession();
        User user= (User) session.getOrDefault("user",null);
        int res=0;
        if (user==null) {
            res = service.add(friend);
        }else {
            //res=service.add(friend,user.getId());
            friend.setUser(user);
            res=service.add(friend);
        }

        if (res!=0){
            msg.put("resCode",1);
            msg.put("message","添加成功");
            return "success";
        }else {
            msg.put("resCode",-1);
            msg.put("message","添加失败");
            return "error";
        }
    }

    public String queryFriends(){
        msg=new HashMap<>();
        List<Object> list=null;
        Map<String,Object> session=ActionContext.getContext().getSession();
        User user= (User) session.getOrDefault("user",null);
        System.out.println(pageNum+"  "+pNum);

        Map<String,Object> conditions=new HashMap<>();
        if(friend!=null){
            if (friend.getName()!=null&&!friend.getName().equals("")){
                conditions.put("name",friend.getName());
            }
            if (friend.getSex()!=-1){
                conditions.put("sex",friend.getSex());
            }
            if (friend.getMobile()!=null&&!friend.getMobile().equals("")){
                conditions.put("mobile",friend.getMobile());
            }
        }
        conditions.put("pageNum",pageNum);
        conditions.put("pNum",pNum);
        if (user!=null){
            conditions.put("uid",user.getId());
        }
        int maxNum=service.queryCountByConditions(conditions);
        list=service.queryByConditions(conditions);
        if (maxNum%pNum==0){
            maxNum=maxNum/pNum;
            if (maxNum==0){
                maxNum=1;
            }
        }else{
            maxNum=maxNum/pNum+1;
        }

        if (list!=null&&list.size()>0){

            msg.put("resCode",1);
            msg.put("message","有数据");
            msg.put("friends",list);
            msg.put("maxPage",maxNum);
            return "success";

        }else {
            msg.put("resCode",-1);
            msg.put("message","无数据");
            return "error";
        }
    }
}
