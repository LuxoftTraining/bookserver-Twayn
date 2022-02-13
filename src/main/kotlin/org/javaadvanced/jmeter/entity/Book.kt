package org.javaadvanced.jmeter.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Book(
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    val id: Long? = null,
    var title: String = "",

    @OneToMany(cascade = [CascadeType.ALL], fetch = LAZY, mappedBy = "book")
    var grid: Set<BookSearchGrid> = setOf()
)