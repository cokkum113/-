package com.example.chap01_userinfo;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

public class UserService {
    private UserDao userDao;
    private DataSource dataSource;
    private PlatformTransactionManager transactionManager;

    void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    

    /*
    public void upgradeLevels(){
        List<User> users = userDao.getAll();
        for (User user : users) {
            Boolean changed = null; //레벨의 변화가 있는지를 확인하는 플래그
            if (user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
                user.setLevel(Level.SILVER);
                changed = true;
            } else if (user.getLevel() == Level.SILVER && user.getLogin() >= 30) {
                user.setLevel(Level.GOLD);
                changed = true;
            } else if (user.getLevel() == Level.GOLD) {
                changed = false;
            } else {
                changed = false;
            }
            if (changed) {
                //레벨 변경이 있는 경우에만 호출
                userDao.update(user);
            }
        }
    }

     */

    public void upgradeLevels()  {
//        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        //JDBC 트랜잭션 추상오브젝트 생성
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }
            this.transactionManager.commit(status);
        } catch (RuntimeException e) {
            this.transactionManager.rollback(status);
            throw  e;
        }


        }


        public static final int MIN_LOGOUT_FOR_SILVER = 50;
        public static final int MIN_RECOMMEND_FOR_GOLD = 30;


        private boolean canUpgradeLevel (User user){
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

        protected void upgradeLevel (User user){
            user.upgradeLevel();
            userDao.update(user);
        }

        public void add (User user){
            if (user.getLevel() == null) {
                user.setLevel(Level.BASIC);
            }
            userDao.add(user);
        }

    public void setDataSource(DriverManagerDataSource dataSource) {
    }


}
