package org.javaadvanced.jmeter.persistence

import org.javaadvanced.jmeter.entity.Book
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<Book, Long> {
    @Query("select b from Book b where b.id in (SELECT book FROM BookSearchGrid where keyword in :keys group by book having count(book) = :keysCount)")
    fun fetchByKeywords(keys: List<String>, keysCount: Long): MutableIterable<Book>
}