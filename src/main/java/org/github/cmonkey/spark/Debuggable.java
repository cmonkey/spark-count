package org.github.cmonkey.spark;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Debuggable {

    default String debug(){

        StringBuilder builder = new StringBuilder(this.getClass().getName());
        builder.append(" [ ");

        Field[] fields = this.getClass().getDeclaredFields();

        Arrays.stream(fields).forEach(f -> {

            f.setAccessible(true);

            try {
                builder.append(f.getName())
                        .append(" = ")
                        .append(f.get(this))
                        .append( " , ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        builder.append( " ] ");

        return builder.toString();
    }
}
