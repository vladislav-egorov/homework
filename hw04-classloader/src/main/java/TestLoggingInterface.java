interface TestLoggingInterface {
    @Log
    void calculation(int param);

    void withOutLogCalculation(int param);
}
