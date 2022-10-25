package coroutines.coroutine_builders

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//launch() and async() coroutines tell the program to not wait for their bodies to complete, but instead to continue its execution, they *immediately* return to the calling method (main() in this case) to execute its next line of code.

fun main() { runBlocking {

    println("starting my three coroutines")

    // launch() is used to "fire and forget" coroutines. It returns immediately when called, returning a Job instance
    val job = launch { someLongRunningWorkForLaunch() }
    println("launch() returns immediately, returning a Job instance $job, while its body is executed asynchronously")

    // async() is used to fire and forget coroutines. It returns immediately when called, returning a Deferred instance, which we can optionally invoke await() on to wait for the completion of this value asynchronously (without blocking the thread) and return the resulting value. A Deferred instance is a Job, if we don't make use of the await() function, then there's zero difference between launch() and async()
    val deferred = async { someLongRunningWorkForAsync() }
    println("async() returns immediately, returning a Deferred instance $deferred, while its body is executed asynchronously")

    // runBlocking is used to run tasks by blocking whichever thread it’s called on until its code completes execution line by line, this defeats the whole purpose of coroutine's since it doesn't do any concurrent, asynchronous, non-blocking codee like async() and launch() are
    val result = runBlocking { someLongRunningWorkForRunBlocking() }
    println("runBlocking() does NOT return immediately, its body will block, and then return the result = $result")

}
}


suspend fun someLongRunningWorkForLaunch(): Boolean {
    delay(3000)
    println("I'm a long running work for launch()")
    return true
}

suspend fun someLongRunningWorkForAsync(): Boolean {
    delay(3000)
    println("I'm a long running work for async()")
    return true
}

suspend fun someLongRunningWorkForRunBlocking(): Boolean {
    delay(3000)
    println("I'm a long running work for runBlocking()")
    return true
}