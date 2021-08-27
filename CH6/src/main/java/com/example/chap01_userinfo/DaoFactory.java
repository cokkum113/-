package com.example.chap01_userinfo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;


//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보
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

        return dataSource;
    }


    //DataSource 타입의 빈을 DI 받는 userDao() 빈 정의 메소드
    @Bean
    public UserDaoJdbc userDao()
    {
        UserDaoJdbc userDao = new UserDaoJdbc();
        userDao.setDataSource(dataSource());
        return userDao;
    }



}
