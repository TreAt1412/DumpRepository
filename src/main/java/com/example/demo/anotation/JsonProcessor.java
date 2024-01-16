package com.example.demo.anotation;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.Optional;

public class JsonProcessor {
    public static String toJson(Object object) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder sb= new StringBuilder();
        Class<?> clazz = object.getClass();
        JsonName jsonClassName = clazz.getDeclaredAnnotation(JsonName.class);
        sb.append("{\n")
                .append("\t\"")
                .append(Optional.ofNullable(jsonClassName).map(JsonName::value).orElse(clazz.getSimpleName()))
                .append("\": {\n}");

        Field fields[] = clazz.getDeclaredFields();
        for (int i = 0;i < fields.length; i++){
            fields[i].setAccessible(true);

            JsonName jsonFieldName = fields[i].getDeclaredAnnotation(JsonName.class);

            sb.append("\t\t\"")
                    // Lay gia tri cua Annotation, neu annotation la null thi lay ten field thay the
                    .append(Optional.ofNullable(jsonFieldName).map(JsonName::value).orElse(fields[i].getName())) // L
                    .append("\": ")
                    // Neu field la String hoac Object. thi append dau ngoac kep vao
                    .append(fields[i].getType() == String.class || !fields[i].getType().isPrimitive() ? "\"" : "")
                    // Lay gia tri cua field
                    .append(fields[i].get(object))
                    // Neu field la String hoac Object. thi append dau ngoac kep vao
                    .append(fields[i].getType() == String.class || !fields[i].getType().isPrimitive()? "\"" : "")
                    // Nếu là field cuối cùng, thì không append dấu ","
                    .append(i != fields.length -1 ? ",\n" : "\n");
        }
        sb.append("\t}\n");
        sb.append("}");

        return sb.toString();
    }
}
