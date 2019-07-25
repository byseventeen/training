package com.lmc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    static String url = "jdbc:mysql://localhost:3306/imooc" ;
    static String username = "root" ;
    static String password = "root" ;
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username,password);//连接数据库
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

}
