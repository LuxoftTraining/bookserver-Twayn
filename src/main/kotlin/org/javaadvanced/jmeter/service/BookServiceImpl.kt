package org.javaadvanced.jmeter.service

import org.javaadvanced.jmeter.entity.Book
import org.javaadvanced.jmeter.entity.BookSearchGrid
import org.javaadvanced.jmeter.persistence.BookRepository
import org.javaadvanced.jmeter.service.cache.CachedBookAccessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {
    @Autowired
    private lateinit var cachedLayer: CachedBookAccessor

    @Autowired
    private lateinit var bookRepository: BookRepository

    companion object {
        val cache = HashMap<String, HashSet<Long?>>()
    }

    override fun store(title: String) {
        val book = Book().also { book ->
            book.title = title
            book.grid = grid(title, book)
        }
        bookRepository.save(book)

        title.splitted().map {
            cache.computeIfAbsent(it) { HashSet() }.add(book.id)
        }
    }

    private fun grid(title: String, book: Book): Set<BookSearchGrid> {
        return title.splitted().map { keyword ->
            BookSearchGrid().also {
                it.book = book
                it.keyword = keyword
            }
        }.toSet()
    }

    override fun fetchByKeyword(keyword: String): Iterable<String> {
        val books = keyword.splitted().mapNotNull { cache[it] }
        val intersect = Tool.intersect(books)

        return cachedLayer.findAllById(intersect).map { it.title }
    }

//    override fun fetchByKeyword(keyword: String): Iterable<String> {
//        val keywords = keyword.splitted()
//
//        return cachedLayer
//            .fetchByKeywords(keywords, keywords.size.toLong())
//            .map { it.title }
//    }

    override fun fetchAllTitles(): Iterable<String> {
        return bookRepository.findAll().map { it.title }
    }

    private fun String.splitted(): List<String> {
        return this.lowercase().split("\\s+".toRegex())
    }
}