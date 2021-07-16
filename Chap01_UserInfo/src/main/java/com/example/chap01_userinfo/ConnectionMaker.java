package com.example.chap01_userinfo;

/*
인터페이스를 만든다.
인터페이스의 구현은 상속받아서 클래스에서 구현하기
 */

import java.sql.Connection;
import java.sql.SQLException;
public interface ConnectionMaker
{
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
