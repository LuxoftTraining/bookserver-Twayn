package org.javaadvanced.jmeter.service

interface BookService {
    fun store(title: String)
    fun fetchAllTitles(): Iterable<String>
    fun fetchByKeyword(keyword: String): Iterable<String>
}