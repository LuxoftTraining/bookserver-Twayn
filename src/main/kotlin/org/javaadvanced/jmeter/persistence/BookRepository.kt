package org.javaadvanced.jmeter.persistence

import org.javaadvanced.jmeter.entity.Book
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Cacheable

@Repository
@Cacheable
interface BookRepository : CrudRepository<Book, Long>