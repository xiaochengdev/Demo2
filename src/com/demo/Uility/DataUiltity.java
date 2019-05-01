package com.demo.Uility;

import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataUiltity {
    private static final String user="root";
    private static final String passwd="xc122311";
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/demo3";


    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("连接失败....，url 问题");
        }
        System.out.println("连接成功....");
        return con;
    }


}
