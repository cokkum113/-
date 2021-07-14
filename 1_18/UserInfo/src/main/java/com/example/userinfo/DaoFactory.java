package com.example.userinfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory
{
    @Bean
    public UserDao NaverUserDao()
    {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker()
    {
        return new NaverConnectionMaker();
    }


}
