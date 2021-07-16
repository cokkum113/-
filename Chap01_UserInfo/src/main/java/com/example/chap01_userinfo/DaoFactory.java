package com.example.chap01_userinfo;

/*
오브젝트 팩토리를 이용한 스프링 IoC
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory
{
    @Bean
    public DataSource dataSource()
    {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:1234/springbook");
        dataSource.setUsername("root");
        dataSource.setPassword("6203");
        //오브젝트 레벨에서 DB 연결방식을 변경할 수 있도록 DB연결 정보를 수정자 메소드를 통해 넣어준다

        return dataSource;
    }
    @Bean
    public UserDao userDao()
    {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }
}
