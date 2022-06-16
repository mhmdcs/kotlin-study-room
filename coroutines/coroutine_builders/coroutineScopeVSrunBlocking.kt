package coroutines.coroutine_builders

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//https://www.youtube.com/watch?v=k_xRxXoimSw&ab_channel=CodePalace&loop=0
//runBlocking and coroutineScope essentially do the same thing: they both wait for the body and all their children to complete before they move on.
//runBlocking blocks the current thread while the code inside it executes. Whatever code you put inside runBlocking, it *has* to be executed.
//coroutineScope suspends(unblocks) the current thread while the code inside it executes.

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

suspend fun main(){
    networkRequest()
}

private suspend fun networkRequest(){

    val time = measureTimeMillis {
        // Property job will be used to control the lifecycle of the coroutine.
        val job = GlobalScope.launch(Dispatchers.IO) {
            log("Starting job...")
            runBlocking {
                delay(1_000)
                log("Starting runBlocking...")
                delay(1_000)
                log("runBlocking...")
            }// If you swap runBlocking coroutine builder with coroutineScope, the job will successfully cancel after 400 milliseconds.
            coroutineScope {
                delay(1_000)
                log("Starting coroutineScope...")
                delay(1_000)
                log("coroutineScope...")
            }
        }
        delay(400)
        log("Cancelling job...")
        job.cancel() // Cancels this scope, including its job and all its children (gives the coroutine a request to finish doing what is doing).
        job.join() // Suspends the coroutine until this job is complete (it waits until everything inside `job` completes).
        log("Done!") // As soon as everything inside job is completed, we want to log that everything was completed.
    }

    log("Time = $time")

}



//Usually, runBlocking it is used in unit tests in Android, or in some other cases of synchronous code.
//runBlocking is not recommended for production code.