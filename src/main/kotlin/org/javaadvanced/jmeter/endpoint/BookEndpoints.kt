package org.javaadvanced.jmeter.endpoint

import org.javaadvanced.jmeter.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BookEndpoints {
    @Autowired
    private lateinit var service: BookService

    @CacheEvict(value=["books"], allEntries=true)
    @GetMapping("/addBook/{title}")
    fun addBook(@PathVariable title: String): String {
        service.store(title)

        return "Added new book with title: '$title'"
    }

    @GetMapping("/getAllBooks")
    fun fetchAll(): Iterable<String> {
        return service.fetchAllTitles()
    }

    @GetMapping("/getByKeyword/{keyword}")
    fun findBookByKeyword(@PathVariable keyword: String): Iterable<String> {
        return service.fetchByKeyword(keyword)
    }
}