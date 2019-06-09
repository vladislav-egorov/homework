package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) {
        int[] acqBins = {709321, 902284};
        List<String> products = new ArrayList<>();
        products.add("MDT");
        products.add("MCD");
        PojoBank pojoBank = new PojoBank(12344, "VTB24", acqBins, products);
        //System.out.println(pojoBank);
        String json = Gson.toJson(pojoBank);
        System.out.println(json);
    }
}
