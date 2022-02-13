package org.javaadvanced.jmeter.generate

import org.javaadvanced.jmeter.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class TestDataGenerator {
    @Autowired
    private lateinit var service: BookService

    @EventListener(ApplicationReadyEvent::class)
    fun generateData() {
        generateRandomData()
        generateMeaningfulData()
        println("Data generated")
    }

    private fun generateMeaningfulData() {
        service.store("Holy Bible by God")
        service.store("Gangsta Rap Coloring Book")
        service.store("How to Raise Your I.Q. by Eating Gifted Children")
        service.store("How to Date a White Woman: A Practical Guide for Asian Men")
        service.store("How to Avoid Huge Ships (Second Edition)")
        service.store("Cheese Problems Solved")
        service.store("Cheese Problems Unsolved")
    }

    private fun generateRandomData(){
        repeat(100_000) { bookIndex ->
            val nameIndex = Random.nextInt(1, 200_000)
            val surnameIndex = Random.nextInt(1, 200_000)

            service.store(bookName(bookIndex, nameIndex, surnameIndex))
        }
    }

    companion object {
        fun bookName(bookIndex: Int, nameIndex: Int, surnameIndex: Int): String {
            return "Book${bookIndex} by AuthorName${nameIndex} AuthorSurname${surnameIndex}"
        }
    }
}