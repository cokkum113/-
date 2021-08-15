package com.example.chap01_userinfo;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
