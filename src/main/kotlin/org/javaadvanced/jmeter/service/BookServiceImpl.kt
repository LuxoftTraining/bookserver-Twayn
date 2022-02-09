package org.javaadvanced.jmeter.service

import org.javaadvanced.jmeter.entity.Book
import org.springframework.beans.factory.annotation.Autowired
import org.javaadvanced.jmeter.service.cache.CachedRepositoryImpl
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {
    @Autowired
    private lateinit var cachedRepository: CachedRepositoryImpl

    override fun store(title: String) {
        cachedRepository.store(Book().also {
            it.title = title
        })
    }

    override fun fetchAll(): Iterable<Book> {
        return cachedRepository.findAll()
    }

    override fun fetchByKeyword(keyword: String): Iterable<Book> {
        val keywords = keyword.splitted()
        return fetchAll().filter { it.title.splitted().containsAll(keywords) }
    }

    fun String.splitted(): List<String> {
        return this.lowercase().split("\\s+".toRegex())
    }
}