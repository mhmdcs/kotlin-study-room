package coroutines.coroutine_builders

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// on the subject of structured concurrency & launch/runBlocking vs async
// https://kotlinlang.org/docs/composing-suspending-functions.html#structured-concurrency-with-async

/**
 * Conceptually, async is just like launch. It starts a separate coroutine which is a light-weight thread that works concurrently with all the other coroutines.
 * Note while coroutines are run concurrently with each other, each coroutine's block of code/body is executed *sequentially*.
 *
 * The difference between launch  and async is that launch returns a Job and does not carry any resulting value,
 * while async returns a Deferred — a light-weight non-blocking future that represents a promise to provide a result later.
 * You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.
 * */

fun main() {
    runBlocking {

        // Sequential by default (using launch or runBlocking builders)
        // The code inside coroutine blocks, just like in the regular code, is sequential by default.
        // This means that doSomethingUsefulOne() and doSomethingUsefulTwo() will be executed line by line, doSomethingUsefulOne() will be executed first, then doSomethingUsefulTwo()
        // The following example demonstrates it by measuring the total time it takes to execute both suspending functions.
        launch {
            val time = measureTimeMillis {
                val one = doSomethingUsefulOne()
                val two = doSomethingUsefulTwo()
                println("The answer of $one + $two is ${one + two}")
            }
            println("Completed in $time ms - using launch")
        }

        // Concurrent using async builder
        // But what if there are no dependencies between invocations of doSomethingUsefulOne and doSomethingUsefulTwo
        // and we want to get the answer faster, by doing both concurrently? This is where the async coroutine builder comes to help.
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer of ${one.await()} + ${two.await()} is ${one.await() + two.await()}")
        }
        println("Completed in $time ms using - async")
        // Using async is twice as fast, because the two coroutines execute concurrently. Note that concurrency with coroutines is always explicit.

//        try {
//            failedConcurrentSum()
//        } catch(e: ArithmeticException) {
//            println("Computation failed with ArithmeticException")
//        }
    }
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

// structured concurrency:  something goes wrong inside the code of the concurrentSum() function, and it throws an exception,
// all the coroutines that were launched in its scope will be cancelled, rather than continue running in the background even though the operation that initiated the coroutines was aborted.
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

// Note how both the first async and the awaiting parent are cancelled on failure of one of the children (namely, val two):
suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}