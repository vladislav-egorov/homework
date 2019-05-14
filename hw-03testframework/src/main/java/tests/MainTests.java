package tests;

import annotations.After;
import annotations.Before;
import annotations.Test;

public class MainTests implements AbstractTestInterface {

    @Before
    public void before() {
        System.out.println("Test started.");
    }

    @After
    public void after() {
        System.out.println("Test finished.");
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
