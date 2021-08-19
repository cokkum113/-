package com.example.chap01_userinfo;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    void add(Connection c, User user);

    User get(Connection c, String id);

    List<User> getAll();

    public void deleteAll();

    int getCount();

    public void update(Connection c, User user1);


}
