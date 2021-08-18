
package com.example.chap01_userinfo;


import java.sql.Connection;
import java.sql.SQLException;

//
//부가 기능 추가
//DB 연결 횟수 카운팅 기능이 있는 클래스
//ConnectionMaker를 상속받은 클래스

public class CountingConnectionMaker implements ConnectionMaker
{
    private int count = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker)
    {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException
    {
        this.count++;
        return realConnectionMaker.makeConnection();
    }

    public int getCount()
    {
        return this.count;
    }
}

