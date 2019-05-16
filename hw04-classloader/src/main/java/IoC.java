import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class IoC {

    private static Set<String> methodsWithLogAnnotation = new HashSet<>();

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            Method[] allClassMethods = myClass.getClass().getDeclaredMethods();
            Arrays.stream(allClassMethods).forEach(
                    method -> {
                        if (method.isAnnotationPresent(Log.class)) {
                            methodsWithLogAnnotation.add(
                                    method.getName() + Arrays.toString(method.getParameters()));
                        }
                    }
            );
            this.myClass = myClass;
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