/*
package com.example.chap01_userinfo;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class FactoryBeanTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        Assertions.assertThat(message).isEqualTo(Message.class);
        Assertions.assertThat(((Message)message).getText()).isEqualTo("Factory Bean");
    }

    @Test
    public void getFactoryBean() throws Exception {
        Object factory = context.getBean("&message");
        Assertions.assertThat(factory).isEqualTo(MessageFactoryBean.class);
    }
}


 */