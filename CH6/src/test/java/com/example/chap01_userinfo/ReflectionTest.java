package com.example.chap01_userinfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        //length()
        assertThat(name.length()).isEqualTo(6);

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer) lengthMethod.invoke(name)).isEqualTo(6);

        //charAt()
        assertThat(name.charAt(0)).isEqualTo('S');

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character) charAtMethod.invoke(name, 0)).isEqualTo('S');


    }

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Toby")).isEqualTo("HelloToby");
        assertThat(hello.sayHi("Toby")).isEqualTo("HiToby");
        assertThat(hello.sayThankYou("Toby")).isEqualTo("Thank YouToby");

    }

}
