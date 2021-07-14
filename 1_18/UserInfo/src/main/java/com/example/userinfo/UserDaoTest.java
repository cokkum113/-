package com.example.userinfo;

import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {

        ApplicationContext context = new AnnotationConfigReactiveWebApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("NaverUserDao", UserDao.class);

        User user = new User();
        user.setId("whiteship2");
        user.setName("백기선2");
        user.setPassword("12345");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");
    }
}
