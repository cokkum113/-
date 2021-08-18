package com.example.chap01_userinfo;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CalcTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setup() throws Exception {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("numbers").getPath();
    }


    @Test
    public void sumOfNums() throws IOException {

        Assertions.assertThat(calculator.calcSum(this.numFilepath)).isEqualTo(10);
    }
}
