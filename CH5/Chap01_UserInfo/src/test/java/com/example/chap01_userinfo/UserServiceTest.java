package com.example.chap01_userinfo;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/application.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;


    @Test
    public void bean() {
        Assertions.assertThat(this.userService).isNotNull();
    }

    List<User> users;

    @Before
    public void setUp() {
        //배열을 리스트로 만들어주는 편리한 메소드. 배열을 가변인자로 넣어주면 더욱 편리하다.
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
                new User("joytouch", "강명성", "p2", Level.BASIC, 50, 0),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, 29),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, 100)
        );
    }


    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }

        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.GOLD);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);

    }
    private void checkLevel(User user, Level expectedLevel)
    {
        User userUpdate = userDao.get(user.getId());
        Assertions.assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }





    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4); //GOLD 레벨이 이미 지정된 User라면 레벨을 초기화하지 않아야함.
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);
        //레벨이 비어있는 사용자. 로직에 따라 등록

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        Assertions.assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevel.getLevel());
        Assertions.assertThat(userWithoutLevelRead.getLevel()).isEqualTo(userWithoutLevel.getLevel());
    }

}
