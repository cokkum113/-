package com.example.chap01_userinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {

    Hello target;

    public UppercaseHandler(Hello target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String ret = (String) method.invoke(target, args);
        //리플렉션 API를 이용해 타깃 호출
        return ret.toUpperCase();
        //부가기능
    }
}
