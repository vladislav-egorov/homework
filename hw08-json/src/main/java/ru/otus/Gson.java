package ru.otus;

import com.google.common.base.Joiner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Gson {
    public static String toJson(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        var stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        AtomicReference<String> delimiter = new AtomicReference<>("");
        Arrays.stream(fields).forEach(
                field -> {
                    stringBuilder.append(delimiter.get());
                    var type = field.getType();
                    if (type.equals(int.class)) {
                        try {
                            stringBuilder.append(createJsonFromInt(field, object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals(String.class)) {
                        try {
                            stringBuilder.append(createJsonFromString(field, object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else if (type.isArray()) {
                        try {
                            stringBuilder.append(createJsonFromIntArray(field, object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else if (Collection.class.isAssignableFrom(type)) {
                        try {
                            stringBuilder.append(createJsonFromCollection(field, object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    delimiter.set(",\n");
                }
        );
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    private static String createJsonFromString(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        String result = jsonConcatString(field.getName(), field.get(object).toString());
        field.setAccessible(false);
        return result;
    }

    private static String createJsonFromInt(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        String result = jsonConcatInt(field.getName(), field.get(object).toString());
        field.setAccessible(false);
        return result;
    }

    private static String createJsonFromIntArray(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        //todo как-то убрать хардкод интов
        int[] array = (int[]) field.get(object);
        String result = jsonConcatForIntArray(field.getName(), array);
        field.setAccessible(false);
        return result;
    }

    private static String createJsonFromCollection(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        Collection collection = (Collection) field.get(object);
        String result = jsonConcatForStringCollection(field.getName(), collection);
        field.setAccessible(false);
        return result;
    }

    private static String jsonConcatString(String s1, String s2) {
        return "\"" + s1 + "\" : \"" + s2 + "\"";
    }

    private static String jsonConcatInt(String s1, String s2) {
        return "\"" + s1 + "\" : " + s2;
    }

    private static String jsonConcatForIntCollection(String name, Collection collection) {
        return "\"" + name + "\" : [" + Joiner.on(", ").join(collection) + "]";
    }

    private static String jsonConcatForStringCollection(String name, Collection collection) {
        return "\"" + name + "\" : [\"" + Joiner.on("\", \"").join(collection) + "\"]";
    }

    private static String jsonConcatForIntArray(String name, int[] array) {
        List<Integer> intsList = new ArrayList<>();
        for (var digit : array
        ) {
            intsList.add(digit);
        }
        return jsonConcatForIntCollection(name, intsList);
    }
}
