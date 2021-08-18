package com.example.chap01_userinfo;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/application.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void bean() {
        Assertions.assertThat(this.userService).isNotNull();
    }


}
