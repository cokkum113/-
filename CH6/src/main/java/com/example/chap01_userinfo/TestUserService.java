package com.example.chap01_userinfo;

public class TestUserService extends UserService {
    private String id;

    TestUserService(String id) {
        this.id = id;
    }

    @Override
    public void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);
    }

    static class TestUserServiceException extends RuntimeException {
    }

}
