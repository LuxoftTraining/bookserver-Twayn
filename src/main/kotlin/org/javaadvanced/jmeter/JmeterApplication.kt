package org.javaadvanced.jmeter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class JmeterApplication

fun main(args: Array<String>) {
    runApplication<JmeterApplication>(*args)
}
