package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class IoC {

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private Set<String> methodsWithLogAnnotation;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.methodsWithLogAnnotation = getKeysForAllMethodsWithAnnotation(
                    myClass.getClass().getDeclaredMethods(),
                    Log.class);
            this.myClass = myClass;
        }

        private Set<String> getKeysForAllMethodsWithAnnotation(Method[] allClassMethods, Class annotationClass) {
            Set<String> resultSet = new HashSet<>();
            Arrays.stream(allClassMethods).forEach(
                    method -> {
                        if (method.isAnnotationPresent(annotationClass)) {
                            resultSet.add(
                                    method.getName() + Arrays.toString(method.getParameters()));
                        }
                    }
            );
            return resultSet;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsWithLogAnnotation.contains(method.getName() + Arrays.toString(method.getParameters()))) {
                System.out.println("executed method: " + method.getName() + " param: " + args[0].toString());
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }

}