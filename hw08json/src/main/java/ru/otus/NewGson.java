package ru.otus;

import com.google.common.base.Joiner;

import java.lang.reflect.Array;
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
        if (noParsingClasses.contains(clazz)) {
            return object.toString();
        } else if (clazz.equals(String.class) || clazz.equals(Character.class)) {
            return "\"" + object + "\"";
        } else if (clazz.isArray()) {
            List<Object> arrayAsList = new ArrayList<>();
            for (int i = 0; i < Array.getLength(object); i++) {
                arrayAsList.add(Array.get(object, i));
            }
            return parseCollection(arrayAsList);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return parseCollection((Collection) object);
        }
        return null;
    }

    private static String parseField(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        String result = jsonConcatString(field.getName(), parseValue(field.get(object)));
        field.setAccessible(false);
        return result;
    }

    private static String jsonConcatString(String s1, String s2) {
        return "\"" + s1 + "\":" + s2 + "";
    }

    @SuppressWarnings("unchecked")
    private static String parseCollection(Collection collection) {
        List<String> printList = new ArrayList<>();
        collection.forEach(
                o -> printList.add(parseValue(o))
        );
        return "[" + Joiner.on(",").join(printList) + "]";
    }
}
