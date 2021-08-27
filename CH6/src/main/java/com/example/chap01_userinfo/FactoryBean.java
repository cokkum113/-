package com.example.chap01_userinfo;

public interface FactoryBean<T> {
    T getObject() throws Exception;
    Class<? extends T> getObjectType();
    boolean isSingleton();
}
