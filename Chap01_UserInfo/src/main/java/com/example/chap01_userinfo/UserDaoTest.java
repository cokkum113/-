package com.example.chap01_userinfo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

/*
UserDao 클라이언트 테스트

 */
public class UserDaoTest
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        //XML을 사용해서 애플리케이션 테스트하기
        ApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship2");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user.getPassword());

        System.out.println(user2.getId() + "조회성공");

    }
}
