public class TestLogging implements TestLoggingInterface {

    @Log
    public void calculation(int param) {
        System.out.println(param + 256);
    }
}
