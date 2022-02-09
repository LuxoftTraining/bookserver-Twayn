package org.javaadvanced.jmeter.service.cache;

import org.javaadvanced.jmeter.entity.Book;
import org.javaadvanced.jmeter.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedRepositoryImpl implements CachedRepository {
    @Autowired
    private BookRepository repository;

    @Override
    public void store(Book book) {
        repository.save(book);
    }

    @Cacheable("books")
    public Iterable<Book> findAll() {
        return repository.findAll();
    }
}
