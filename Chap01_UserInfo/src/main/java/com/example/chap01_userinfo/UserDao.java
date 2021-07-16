package com.example.chap01_userinfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
조회 기능이 있는 UserDao 클래스
 */
public class UserDao
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    //사용자 데이터 추가
    public void add(User user) throws ClassNotFoundException, SQLException
    {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate(); //만들어진 ps를 실행한다.

        ps.close();
        c.close();
    }

    //사용자 데이터 가져오기
    public User get(String id) throws ClassNotFoundException, SQLException
    {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setName(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
