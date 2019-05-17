package ru.otus;

public class TestLogging implements TestLoggingInterface {

    @Log
    public void calculation(int param) {
        System.out.println(param + 256);
    }

    public void withOutLogCalculation(int param) {
        System.out.println(param + 256);
    }
}
