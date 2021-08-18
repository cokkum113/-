package com.example.chap01_userinfo;

import java.util.List;

public class UserService {
   UserDao userDao;

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

    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= 50);
            case SILVER:
                return (user.getLogin() >= 30);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level : " + currentLevel);
                //현재 로직에서 다룰 수 없는 레벨이 주어지면 예외를 발생시킨다.
                //새로운 레벨이 추가되고 로직을 수정하지 않으면 에러가 나서 확인할 수 있다.
        }
    }

    private void upgradeLevel(User user) {
        if(user.getLevel() == Level.BASIC) user.setLevel(Level.SILVER);
        else if(user.getLevel() == Level.SILVER) user.setLevel(Level.GOLD);
        userDao.update(user);
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }
}
