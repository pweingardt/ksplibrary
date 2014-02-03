package de.ksp.library;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {

    public static <T> String join(Collection<T> s, String delimiter,
                                  Builder<T> builder) {
        if (s.isEmpty()) return "";
        Iterator<T> iter = s.iterator();
        StringBuilder buffer = new StringBuilder(builder.build(iter.next()));
        while (iter.hasNext()) buffer.append(delimiter)
                .append(builder.build(iter.next()));
        return buffer.toString();
    }

    public static String join(Collection<String> s, String delimiter) {
        if (s.isEmpty()) return "";
        Iterator<String> iter = s.iterator();
        StringBuilder buffer = new StringBuilder(iter.next());
        while (iter.hasNext()) buffer.append(delimiter)
                .append(iter.next());
        return buffer.toString();
    }

    public interface Builder<T> {
        public String build(T o);
    }
}
