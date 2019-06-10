package ru.otus;

import com.google.common.base.Joiner;
import com.google.common.primitives.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class NewGson {
    private static final Set<Class> noParsingClasses = Set.of(
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class
    );

    public static String toJson(Object object) {
        if (object == null) {
            return "null";
        }
        Class clazz = object.getClass();
        if (noParsingClasses.contains(clazz)
                || clazz.equals(String.class)
                || clazz.equals(Character.class)
                || clazz.isArray()
                || Collection.class.isAssignableFrom(clazz)) {
            return parseValue(object);
        }
        Field[] fields = clazz.getDeclaredFields();
        var stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        AtomicReference<String> delimiter = new AtomicReference<>("");
        Arrays.stream(fields).forEach(
                field -> {
                    stringBuilder.append(delimiter.get());
                    try {
                        stringBuilder.append(parseField(field, object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    delimiter.set(",");
                }
        );
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String parseValue(Object object) {
        Class clazz = object.getClass();
        if (noParsingClasses.contains(clazz)
                || clazz.equals(String.class)
                || clazz.equals(Character.class)) {
            return object.toString();
        } else if (clazz.isArray()) {
            //Я не знаю, как сделать лучше, такой варинт - отстой
            if (byte[].class.equals(clazz)) return parseCollection(Bytes.asList((byte[]) object));
            else if (short[].class.equals(clazz)) return parseCollection(Shorts.asList((short[]) object));
            else if (int[].class.equals(clazz)) return parseCollection(Ints.asList((int[]) object));
            else if (long[].class.equals(clazz)) return parseCollection(Longs.asList((long[]) object));
            else if (float[].class.equals(clazz)) return parseCollection(Floats.asList((float[]) object));
            else if (double[].class.equals(clazz)) return parseCollection(Doubles.asList((double[]) object));
            else parseArray((Object[]) object);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return parseCollection((Collection) object);
        }
        return null;
    }

    private static String parseField(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        String result = jsonConcatString(field.getName(), parseValue(field.get(object)),
                !noParsingClasses.contains(field.get(object).getClass()));
        field.setAccessible(false);
        return result;
    }

    private static String jsonConcatString(String s1, String s2, boolean isQuotesNeeded) {
        if (s2.startsWith("[") || !isQuotesNeeded) {
            return "\"" + s1 + "\":" + s2 + "";
        } else {
            return "\"" + s1 + "\":\"" + s2 + "\"";
        }
    }

    private static String parseCollection(Collection collection) {
        List<String> printList = new ArrayList<>();
        collection.forEach(
                o -> {
                    if (noParsingClasses.contains(o.getClass())) {
                        printList.add(o.toString());
                    } else {
                        printList.add("\"" + o.toString() + "\"");
                    }
                }
        );
        return "[" + Joiner.on(",").join(printList) + "]";
    }

    private static <T> String parseArray(T[] array) {
        return parseCollection(Arrays.asList(array));
    }
}
