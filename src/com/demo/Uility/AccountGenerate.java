package com.demo.Uility;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountGenerate {
    public static String generateAccount(){
        int count=0;
        Connection con=DataUiltity.getConnection();
        StringBuilder buffer=new StringBuilder("");
        String sql="select count(*) count from user where account=?";
        for (int i=0;i<6;i++){
            int rand=(int)(Math.random()*10);
            buffer.append(rand);
        }
        String account=buffer.toString();
        try {
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,account);
            ResultSet res=preparedStatement.executeQuery();
            while (res.next()){
                count=res.getInt("count");
            }
            res.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count>0){
            generateAccount();
        }
        return account;
    }
}
