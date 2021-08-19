package com.example.chap01_userinfo;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class UserService {
    UserDao userDao;
    private DataSource dataSource;

    private void setDataSource(DataSource dataSource) {
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

    public void upgradeLevels() throws Exception {
        TransactionSynchronizationManager.initSynchronization();
        //트랜잭션 동기화 관리자를 이용해 동기화 작업을 초기화한다
        Connection c = DataSourceUtils.getConnection(dataSource);
        c.setAutoCommit(false);
        //DB 커넥션을 생성하고 트랜잭션을 시작한다. 이후의 DAO 작업은 모두 여기서 시작한 트랜잭션 안에서 진행된다.
        //DB 커넥션 생성과 동기화를 함께해주는 유틸리티 매소드

        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }

            }
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally {
            DataSourceUtils.releaseConnection(c, dataSource);
            //스프링 유틸리티 메소드를 이용해 DB커넥션을 안전하게 닫는다.
            TransactionSynchronizationManager.unbindResource(this.dataSource);
            TransactionSynchronizationManager.clearSynchronization();
            //동기화 작업 종료및 정리
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
    }
