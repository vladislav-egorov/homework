public class TestLogging implements TestLoggingInterface {

    public void calculation(int param) {
        System.out.println(param + 256);
    }

    public void withOutLogCalculation(int param) {
        System.out.println(param + 256);
    }
}
