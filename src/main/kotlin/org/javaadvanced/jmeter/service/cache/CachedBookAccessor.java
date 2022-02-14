package org.javaadvanced.jmeter.service.cache;

import org.javaadvanced.jmeter.entity.Book;

import java.util.List;
import java.util.Set;

public interface CachedBookAccessor {
    Iterable<Book> fetchByKeywords(List<String> keywords, Long keysCount);
    Iterable<Book> findAllById(Set<Long> keywords);
}
