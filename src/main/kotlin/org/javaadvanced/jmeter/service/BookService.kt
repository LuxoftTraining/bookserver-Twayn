package org.javaadvanced.jmeter.service

import org.javaadvanced.jmeter.entity.Book

interface BookService {
    fun store(title: String)

    fun fetchAll(): Iterable<Book>

    fun fetchByKeyword(keyword: String): Iterable<Book>
}