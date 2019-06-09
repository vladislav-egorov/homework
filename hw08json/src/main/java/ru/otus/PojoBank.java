package ru.otus;

import java.util.Arrays;
import java.util.List;

public class PojoBank {
    private int bankId;
    private String bankName;
    private int[] acqBins;
    private List<String> productList;

    public PojoBank(int bankId, String bankName, int[] acqBins, List<String> productList) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.acqBins = acqBins;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "PojoBank{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", acqBins=" + Arrays.toString(acqBins) +
                ", productList=" + productList +
                '}';
    }
}
