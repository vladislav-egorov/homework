package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class MainTests implements AbstractTestInterface {

    @Before
    public void before() {
        System.out.println("Test started.");
    }

    @After
    public void after() {
        throw new RuntimeException("RuntimeException: something is wrong!");
    }


    @Test
    public void succsessTest1() {
        System.out.println("testing....1");
    }

    @Test
    public void succsessTest2() {
        System.out.println("testing....2");
    }

    @Test
    public void negativeTest() {
        throw new RuntimeException("RuntimeException: something is wrong!");
    }

    @Test
    public void succsessTest3() {
        System.out.println("testing....3");
    }
}
