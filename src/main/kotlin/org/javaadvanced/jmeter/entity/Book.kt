package org.javaadvanced.jmeter.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Book(
    @Id
    @GeneratedValue
    val id: Long? = null,
    var title: String = "",
)