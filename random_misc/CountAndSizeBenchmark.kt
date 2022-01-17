package random_misc

import kotlin.system.measureNanoTime

// this program demonstrates how to create a benchmark test
// in this example we're testing performance of count() method and size property for Lists
fun main() {
    val numbers = listOf(4, 5, 3, 2, 1, -1, 7, 6, -8, 9, -12)
    val (filterCount, filterSize) = benchmark(numbers)
    println("Average time taken for filter count call: $filterCount")
    println("Average time taken for filter size call: $filterSize")
}

//Run this benchmark algorithm 10 million times, this should hopefully account for any unrelated differences affected by what's going on in the JVM/System.
fun benchmark(numbers: List<Int>, iterations: Int = 10_000_000): Pair<Long, Long> {
    var filterCount = 0L
    var filterSize = 0L
    repeat(iterations) {
        filterCount += measureNanoTime { numbers.filter { e -> e > 0 }.count() }
        filterSize += measureNanoTime { numbers.filter { e -> e > 0 }.size }
    }
    return Pair(filterCount / iterations, filterSize / iterations)
}