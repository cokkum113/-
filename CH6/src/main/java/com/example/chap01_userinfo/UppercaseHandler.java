package com.example.chap01_userinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UppercaseHandler implements InvocationHandler {

    Object target;

    private UppercaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(target, args);
        if (ret instanceof String && method.getName().startsWith("say")) {
            return ((String) ret).toUpperCase();
        } else {
            return ret;
        }
    }

    Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(),
            new Class[]{Hello.class},
            new UppercaseHandler(new HelloTarget()));

}
