package org.javaadvanced.jmeter.service.cache;

import org.javaadvanced.jmeter.entity.Book;

public interface CachedRepository {
    void store(Book book);
    Iterable<Book> findAll();
}
