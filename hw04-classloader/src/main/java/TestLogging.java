public class TestLogging {

    @Log
    public void calculation(int param) {
        System.out.println(param + 256);
    }
}
