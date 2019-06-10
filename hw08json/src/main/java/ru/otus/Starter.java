package ru.otus;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Starter {
    public static void main(String[] args) {
        int[] acqBins = {709321, 902284};
        List<String> products = new ArrayList<>();
        products.add("MDT");
        products.add("MCD");
        PojoBank pojoBank = new PojoBank(12344, "VTB24", acqBins, products);
        var gson = new Gson();

        System.out.println(NewGson.toJson(pojoBank));
        System.out.println(gson.toJson(pojoBank));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(null));
        System.out.println(gson.toJson(null));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson((byte) 1));
        System.out.println(gson.toJson((byte) 1));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson((short) 1f));
        System.out.println(gson.toJson((short) 1f));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(1));
        System.out.println(gson.toJson(1));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(1L));
        System.out.println(gson.toJson(1L));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(1f));
        System.out.println(gson.toJson(1f));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(1d));
        System.out.println(gson.toJson(1d));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson("aaa"));
        System.out.println(gson.toJson("aaa"));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson('a'));
        System.out.println(gson.toJson('a'));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(new int[]{1, 2, 3}));
        System.out.println(gson.toJson(new int[]{1, 2, 3}));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(List.of(1, 2, 3)));
        System.out.println(gson.toJson(List.of(1, 2, 3)));
        System.out.println("===============================================");

        System.out.println(NewGson.toJson(Collections.singletonList(1)));
        System.out.println(gson.toJson(Collections.singletonList(1)));
    }
}
