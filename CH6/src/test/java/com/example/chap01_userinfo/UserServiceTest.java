package com.example.chap01_userinfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.chap01_userinfo.UserServiceImpl.MIN_LOGOUT_FOR_SILVER;
import static com.example.chap01_userinfo.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/application.xml")
public class UserServiceTest {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;

    @Autowired
    UserDao userDao;

    @Autowired
    DataSource dataSource;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    public void bean() {
        assertThat(this.userService).isNotNull();
    }

    List<User> users;

    @Before
    public void setUp() {
        //배열을 리스트로 만들어주는 편리한 메소드. 배열을 가변인자로 넣어주면 더욱 편리하다.
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGOUT_FOR_SILVER - 1, 0,"@naver.com"),
                new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGOUT_FOR_SILVER, 0,"@naver.com"),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1,"c@naver.com"),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD,"codd@naver.com"),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE,"codingwhizkid@naver.com")
        );

    }




    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel().nextLevel());
        } else {
            assertThat(userUpdate.getLevel()).isEqualTo(user.getLevel());
        }
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

        assertThat(userWithLevelRead.getLevel()).isEqualTo(userWithLevel.getLevel());
        assertThat(userWithoutLevelRead.getLevel()).isEqualTo(userWithoutLevel.getLevel());
    }

    @Test
    public void upgradeAllOrNothing() {
        UserServiceImpl testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setMailSender(mailSender);

        UserServiceTx txUserService = new UserServiceTx();
        txUserService.setTransactionManager(transactionManager);
        txUserService.setUserService(testUserService);

        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }

        try {
            txUserService.upgradeLevels();
            //트랜잭션 기능을 분리한 오브젝트를 통해 예외 발생용 TestUserService가 호출되게 해야한다.
            fail("TestUserServiceException expected");
        } catch (TestUserService.TestUserServiceException e) {
        }

        checkLevelUpgraded(users.get(1), false);

    }

    static class MockMailSender implements MailSender {
        private List<String> requests = new ArrayList<String>();


        public List<String> getRequests() {
            return requests;
        }


        @Override
        public void send(SimpleMailMessage simpleMessage) throws MailException {
            requests.add(simpleMessage.getTo()[0]);

            //전송 요청을 받은 이메일 주소를 저장해둔다. 간단하게 첫번째 수신자 메일 주소만 저장했다.
        }

        @Override
        public void send(SimpleMailMessage... simpleMessages) throws MailException {

        }

    }

    @Test
    @DirtiesContext
    public void upgradeLevels() throws Exception {
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }
        //DB 테스트 준비


        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);
        //메일 발송 여부확인을 위해 목 오브젝트 DI

        userService.upgradeLevels();
        //테스트 대상 실행

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), true);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
        //DB에 저장된 결과 확인

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size()).isEqualTo(2);
        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());
        //목 오브젝트를 이용한 결과 확인.
    }




}
