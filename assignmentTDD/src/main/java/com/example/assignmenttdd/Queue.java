package com.example.assignmenttdd;

public interface Queue {
    void add(Integer value);

    Integer pop();

    boolean full();

    boolean empty();

    int size();

    Integer front();

}
