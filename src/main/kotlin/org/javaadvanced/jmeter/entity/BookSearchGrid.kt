package org.javaadvanced.jmeter.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(
    name = "BOOK_SEARCH_GRID", indexes = [
        Index(columnList = "keyword", name = "keyword_idx")
    ]
)
class BookSearchGrid {
    @Id
    @GeneratedValue
    val id: Long? = null
    var keyword: String = ""

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    var book: Book? = null
}