package ru.otus;

import ru.otus.tests.MainTests;

import java.lang.reflect.InvocationTargetException;

public class TestRunner {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TopkaTester.run(MainTests.class);
    }
}
