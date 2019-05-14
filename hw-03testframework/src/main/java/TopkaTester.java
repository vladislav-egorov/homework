import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopkaTester {

    @SuppressWarnings("unchecked")
    public static void run(Class clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Method[] methods = clazz.getMethods();
        int passed = 0;
        int failed = 0;
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        Arrays.stream(methods).forEach(
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

        for (Method method : testMethods
                ) {
            Object testClassObj = clazz.getDeclaredConstructor().newInstance();
            try {
                for (Method beforeMethod : beforeMethods) {
                    beforeMethod.invoke(testClassObj);
                }
                method.invoke(testClassObj);
                for (Method afterMethod : afterMethods) {
                    afterMethod.invoke(testClassObj);
                }
                passed++;
            } catch (Exception ex) {
                System.out.println("Catch failing test, current failed tests count: " + failed);
                System.out.println(ex.getCause().getMessage());
                failed++;
            }

        }
        System.out.println("Testing results:\nTest passed = " + passed + "\nTest failed = " + failed);
    }

}
