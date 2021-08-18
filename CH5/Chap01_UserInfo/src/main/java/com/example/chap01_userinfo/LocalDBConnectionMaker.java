package com.example.chap01_userinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//개발용과 운영용

public class LocalDBConnectionMaker implements ConnectionMaker
{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:1234/springbook", "root","6203"
        );
        return c;
    }
}

