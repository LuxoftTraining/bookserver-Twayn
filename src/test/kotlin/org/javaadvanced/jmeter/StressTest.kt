package org.javaadvanced.jmeter

import org.hamcrest.MatcherAssert
import org.hamcrest.number.OrderingComparison
import org.junit.jupiter.api.Test
import org.loadtest4j.Request
import org.loadtest4j.factory.LoadTesterFactory
import java.time.Duration

class StressTest {
    @Test
    fun accessUrl() {
        val requests = listOf(Request.get("/getByKeyword/practical%20asian"))
        val result = LoadTesterFactory.getLoadTester().run(requests)

        println("Median request time: ${result.responseTime.median}")
        println("Max request time: ${result.responseTime.max}")
        println("OK %: ${result.percentOk}")
        println("OK request number: ${result.diagnostics.requestCount.ok}")
        println("Total request number: ${result.diagnostics.requestCount.total}")
        println("Throughput (requests/sec): ${result.diagnostics.requestsPerSecond}")
        println("Percentile 90: ${result.responseTime.getPercentile(90.0)}")
        println("Percentile 95: ${result.responseTime.getPercentile(95.0)}")
        println("Percentile 99: ${result.responseTime.getPercentile(99.0)}")

        MatcherAssert.assertThat(result.responseTime.getPercentile(90.0), OrderingComparison.lessThanOrEqualTo(Duration.ofMillis(10000)))
    }
}