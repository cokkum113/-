package com.example.assignmenttdd;

import com.example.assignmenttdd.exception.NoElementException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueTest {
    private Queue q;

    @Before
    public void before() {
        this.q = new QueueImpl();
    }

    @Test
    public void pop_FirstIn_FirstOut() {
        q.add(32);
        q.add(23);
        assertThat(q.pop()).isEqualTo(32);
        assertThat(q.pop()).isEqualTo(23);
    }


    @Test
    public void add_InsertNumbersSpecific_InsertProperly() {
        int x = q.size();
        for (int i = 0; i < 3; i++) {
            q.add(2);
        }
        assertThat(x + 3).isEqualTo(3);
    }

    @Test
    public void empty_EmptyQueue_ReturnTrue() {
        assertThat(q.empty()).isTrue();
    }

    @Test
    public void empty_NotEmptyQueue_ReturnFalse() {
        q.add(2);
        assertThat(q.empty()).isFalse();
    }


    @Test
    public void pop_ExecutePopWhenQIsEmpty_ThrowsNoElementException() {
        Assertions.assertThatThrownBy(() -> q.pop()).isInstanceOf(NoElementException.class);
    }

    @Test
    public void front_ExecuteFrontWhenQIsEmpty_ThrowsNoElementException() {
        Assertions.assertThatThrownBy(() -> q.front()).isInstanceOf(NoElementException.class);
    }

    @Test
    public void full_QueueSizeIs100_True() {
        for (int i = 0; i < 100; i++) {
            q.add(30);
        }
        assertThat(q.full()).isTrue();
    }

    @Test
    public void full_QueueSizeLessThan100_False() {
        for (int i = 0; i < 19; i++) {
            q.add(30);
        }
        assertThat(q.full()).isFalse();
    }


    @Test
    public void front_InsertNum_FrontNumOfQueueIsSame() {
        q.add(32);
        assertThat(q.front()).isEqualTo(32);
    }

    @Test
    public void size_CountOfUsingAddMethod_CountOfElementsInQueue() {
        q.add(32);
        q.add(88);
        q.add(12);
        assertThat(q.size()).isEqualTo(3);
    }
}
