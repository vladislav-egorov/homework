package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TopkaTester {
    private static final String TEST_METHODS_KEY = "testMethods";
    private static final String BEFORE_METHODS_KEY = "beforeMethods";
    private static final String AFTER_METHODS_KEY = "afterMethods";

    @SuppressWarnings("unchecked")
    public static void run(Class clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        var passed = 0;
        var failed = 0;
        var testPlan = createTestPlan(clazz.getMethods());

        for (var method : testPlan.get(TEST_METHODS_KEY)
        ) {
            var isPassed = false;
            var testClassObj = clazz.getDeclaredConstructor().newInstance();
            try {
                for (var beforeMethod : testPlan.get(BEFORE_METHODS_KEY)) {
                    beforeMethod.invoke(testClassObj);
                }
                method.invoke(testClassObj);
                isPassed = true;
                try {
                    for (var afterMethod : testPlan.get(AFTER_METHODS_KEY)) {
                        afterMethod.invoke(testClassObj);
                    }
                } catch (Exception ex) {
                    System.out.println("Catch failing test, current failed tests count: " + failed);
                    System.out.println(ex.getCause().getMessage());
                }
            } catch (Exception ex) {
                System.out.println("Catch failing test, current failed tests count: " + failed);
                System.out.println(ex.getCause().getMessage());
            }
            if (isPassed) {
                passed++;
            } else {
                failed++;
            }
        }
        System.out.println("Testing results:\nTest passed = " + passed + "\nTest failed = " + failed);
    }

    private static Map<String, Set<Method>> createTestPlan(Method[] allClassMethods) {
        Set<Method> beforeMethods = new HashSet<>();
        Set<Method> afterMethods = new HashSet<>();
        Set<Method> testMethods = new HashSet<>();
        Arrays.stream(allClassMethods).forEach(
                method -> {
                    if (method.isAnnotationPresent(Test.class)) {
                        testMethods.add(method);
                    } else {
                        if (method.isAnnotationPresent(Before.class)) {
                            beforeMethods.add(method);
                        } else {
                            if (method.isAnnotationPresent(After.class)) {
                                afterMethods.add(method);
                            }
                        }
                    }
                }
        );
        Map<String, Set<Method>> resultMethodMap = new HashMap<>();
        resultMethodMap.put(TEST_METHODS_KEY, testMethods);
        resultMethodMap.put(BEFORE_METHODS_KEY, beforeMethods);
        resultMethodMap.put(AFTER_METHODS_KEY, afterMethods);
        return resultMethodMap;
    }

}
