package org.javaadvanced.jmeter

import org.hamcrest.MatcherAssert
import org.hamcrest.number.OrderingComparison
import org.javaadvanced.jmeter.generate.TestDataGenerator
import org.junit.jupiter.api.Test
import org.loadtest4j.Request
import org.loadtest4j.factory.LoadTesterFactory
import java.time.Duration
import kotlin.random.Random

class StressTest {
    @Test
    fun accessUrl() {
        val result = LoadTesterFactory.getLoadTester().run(createRequests())

        println("Median request time: ${result.responseTime.median}")
        println("Max request time: ${result.responseTime.max}")
        println("OK %: ${result.percentOk}")
        println("OK request number: ${result.diagnostics.requestCount.ok}")
        println("Total request number: ${result.diagnostics.requestCount.total}")
        println("Throughput (requests/sec): ${result.diagnostics.requestsPerSecond}")
        println("Percentile 90: ${result.responseTime.getPercentile(90.0)}")
        println("Percentile 95: ${result.responseTime.getPercentile(95.0)}")
        println("Percentile 99: ${result.responseTime.getPercentile(99.0)}")

        MatcherAssert.assertThat(result.responseTime.getPercentile(90.0), OrderingComparison.lessThanOrEqualTo(Duration.ofMillis(5000)))
    }

    companion object {
        val keywords = mutableListOf(
            "practical%20asian",
            "by%20bible",
            "avoid",
            "white%20asian",
            "solved%20cheese",
            "eating%20children",
            "book%20rap%20gangsta",
            "cheese",
        )
    }

    private fun createRequests(): List<Request> {
        repeat(20) { bookIndex ->
            val nameIndex = Random.nextInt(1, 200_000)
            val surnameIndex = Random.nextInt(1, 200_000)

            keywords.add(TestDataGenerator.bookName(bookIndex, nameIndex, surnameIndex))
        }

        return keywords.map { Request.get("/getByKeyword/$it") }
    }
}