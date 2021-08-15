package com.example.assignmenttdd;

import com.example.assignmenttdd.exception.NoElementException;
import com.example.assignmenttdd.exception.NoEnoughSpaceException;

public class QueueImpl implements Queue {
    private static final int MAXSIZE = 100;
    private int size = 0;
    private int[] q;
    private int front = 0;
    private int rear = 0;

    public QueueImpl() {
        this.q = new int[MAXSIZE];
    }

    @Override
    public void add(Integer value) {
        if (full()) {
            throw new NoEnoughSpaceException("Queue is full");
        }
        this.q[rear] = value;
        this.rear = (this.rear + 1) % MAXSIZE;
        size++;
    }

    @Override
    public Integer pop() {
        if (empty()) {
            throw new NoElementException("Queue is empty");
        }
        int x = this.q[front];
        this.front = (this.front + 1) % MAXSIZE;
        size--;
        return x;
    }

    @Override
    public boolean full() {
        return size == MAXSIZE;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer front() {
        if (empty()) {
            throw new NoElementException("Queue is empty");
        }
        return this.q[front];
    }
}
