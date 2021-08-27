package com.example.chap01_userinfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private DataSource dataSource;
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }


    void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    public static final int MIN_LOGOUT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;


    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGOUT_FOR_SILVER);
            case SILVER:
                return (user.getLogin() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level : " + currentLevel);
                //현재 로직에서 다룰 수 없는 레벨이 주어지면 예외를 발생시킨다.
                //새로운 레벨이 추가되고 로직을 수정하지 않으면 에러가 나서 확인할 수 있다.
        }
    }

    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeEMail(user);
    }

    private void sendUpgradeEMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        this.mailSender.send(mailMessage);
    }


    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
    }


    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
    }




}
