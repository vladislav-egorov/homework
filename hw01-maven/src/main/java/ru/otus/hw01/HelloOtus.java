package ru.otus.hw01;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class HelloOtus {

    public static void main(String[] args) {
        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("apples");
        shoppingList.add("juice");
        shoppingList.add("coca-cola");
        shoppingList.add("Jack Daniel's");

        String separator = "; ";

        String result = Joiner.on(separator).skipNulls().join(shoppingList);
        System.out.println(result);
    }
}
