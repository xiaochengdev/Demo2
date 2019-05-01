package com.demo.entity.impl;

import com.demo.entity.interfaces.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User  implements Role {
    private int id;
    private String account;
    private String name;
    private String passwd;
    private String sex;
    private String des;
    private String headpicture;
    private Set<Friend> friendSet=new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHeadpicture() {
        return headpicture;
    }

    public void setHeadpicture(String headpicture) {
        this.headpicture = headpicture;
    }

    @Override
    public int getRole() {
        return Role.USER;
    }

    public Set<Friend> getFriendSet() {
        return friendSet;
    }

    public void setFriendSet(Set<Friend> friendSet) {
        this.friendSet = friendSet;
    }
}
