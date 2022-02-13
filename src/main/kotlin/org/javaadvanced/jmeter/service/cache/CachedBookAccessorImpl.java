package org.javaadvanced.jmeter.service.cache;

import org.javaadvanced.jmeter.entity.Book;
import org.javaadvanced.jmeter.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CachedBookAccessorImpl implements CachedBookAccessor {
    @Autowired
    private BookRepository repository;

    @Cacheable("keywords")
    public Iterable<Book> fetchByKeywords(List<String> keywords, Long keysCount) {
        return repository.fetchByKeywords(keywords, keysCount);
    }
}
