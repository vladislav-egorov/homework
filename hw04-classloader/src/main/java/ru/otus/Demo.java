package ru.otus;

public class Demo {

    public static void main(String[] args) {
        IoC.createMyClass().calculation(256);
        IoC.createMyClass().withOutLogCalculation(256);
    }
}
