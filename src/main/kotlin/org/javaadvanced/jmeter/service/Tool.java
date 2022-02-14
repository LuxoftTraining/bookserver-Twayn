package org.javaadvanced.jmeter.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Tool {
    public static <T> HashSet<T> intersect(List<HashSet<T>> setList) {
        if (setList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Iterator<HashSet<T>> it = setList.iterator();
        HashSet<T> result = new HashSet<>(it.next());
        while (it.hasNext()) {
            result.retainAll(it.next());
        }
        return result;
    }
}
