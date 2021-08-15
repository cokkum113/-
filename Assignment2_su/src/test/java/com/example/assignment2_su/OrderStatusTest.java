package com.example.assignment2_su;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.comparator.Comparators;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


/**
 * Optional
 * Funtional Interface + Lambda
 * Spring scheduler
 * Enum
 *
 * */
public class OrderStatusTest {

    @Test
    public void testEnum() {
        ST who1 = ST.findN(2014004066);
        ST who2 = ST.findN(2014004066);
        ST who3 = ST.findN(10);
        ST who4 = ST.findN(5);
        ST who5 = ST.findN(15);


        System.out.println(ST.wrong);

    }



    @Test
    public void streamTest() {
        int[] arr = new int[] {
                15, 1425, 35, 234, 3645, 2345
        };
        Integer[] arr2 = new Integer[] {
                15, 1425, 35, 234, 3645, 2345
        };

        // 가장 큰것 뽑기
        // ===========    ===== ==========
        // 15, 1425, ...    map     max
        // ===========    ===== ==========
//        int max = Arrays.stream(arr).map((x) -> x % 500).max().getAsInt();

        /*
        int max = Arrays.stream(arr).map((x) -> x % 500).max().getAsInt();
        int max2 = Arrays.stream(arr2).max((a, b) -> a.intValue() % 500 - b.intValue() % 500).get();
        System.out.println(max2);


        Arrays.sort(arr2, (a, b) -> a.intValue() - b.intValue());

        Arrays.stream(arr2).forEach(System.out::println);

         */

        // arr2 를 통해 1000보다 작은 것중 가장 큰 것을 뽑아라.
        // filter -> max
        // 1. max new / lambda
        int m = Arrays.stream(arr2).filter((a) -> a < 1000).max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }).get();

        System.out.println("m = " + m);

        int m2 = Arrays.stream(arr2).filter((a) -> a < 1000).max((x, y) -> y - x).get();
        System.out.println("m2 = " + m2);

        /*
        A[] ar = new A[]{new A(2),new A(5), new A(3)};
        //
        Arrays.stream(ar).sorted().mapToInt(x -> x.v).forEach(System.out::println);
        Arrays.stream(ar).sorted().mapToInt(x -> x.v).forEach(System.out::println);
        */


        // 2. A를 v값 기준으로 정렬해서, 이름을 출력하세요.
        A[] ar = new A[]{new A(2, "wang"),new A(5, "hy"), new A(3, "dd")};

        // 1. comparable , 2. comparator
        Arrays.stream(ar).sorted(new Comparator<A>() {
            @Override
            public int compare(A o1, A o2) {
                return o1.v - o2.v;
            }
        }).map(x -> x.name).forEach(System.out::println);

        Arrays.stream(ar).sorted().map(x -> x.name).forEach((x) -> System.out.println(x));

        Arrays.stream(ar).sorted().map(new Function<A, String>() {
            @Override
            public String apply(A a) {
                return a.name;
            }
        }).forEach((x) -> System.out.println(x));

        Arrays.stream(ar).sorted().map(x -> x.name).forEach(System.out::println);

    }

    interface TV {
        void ggggg();
    }

    @AllArgsConstructor
    @Getter
    static class A implements Comparable{
        int v;
        String name;

        @Override
        public int compareTo(Object o) {
            return v - ((A)o).v;
        }
    }


    @RequiredArgsConstructor
    @Getter

    enum ST {
        왕규(2014004066), 혜수(10), ANONYMOUS(-1);

        private final int studentNumber;


        private static int wrong = 0;

        static ST makeSentence() {
            wrong++;
            return ANONYMOUS;
        }
        // ------------------    ---------------   ---------
        // Anonymous, 혜수, 왕규    filter
        // ------------------    ---------------   ---------
        public static ST findN(int studentNumber) {
            return Arrays.stream(values()).filter((t) -> t.studentNumber == studentNumber).findFirst().orElseGet(() -> makeSentence());
        }
    }
}