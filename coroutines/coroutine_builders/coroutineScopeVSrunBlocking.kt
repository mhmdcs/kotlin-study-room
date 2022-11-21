package coroutines.coroutine_builders

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//https://www.youtube.com/watch?v=k_xRxXoimSw&ab_channel=CodePalace&loop=0
//runBlocking and coroutineScope essentially do the same thing: they both wait for the body and all their children to complete before they move on.
//runBlocking blocks the current thread while the code inside it executes. Whatever code you put inside runBlocking, it *has* to be executed.
//coroutineScope suspends(unblocks) the current thread while the code inside it executes.

/***
 * runBlocking() and coroutineScope() are functionally very similar. They both pause the current execution waiting until the inner coroutine finishes.
 * The difference is that runBlocking() is meant to be executed from a regular non-suspendable code, so it waits by blocking and coroutineScope() is used from suspendable context, so it suspends.

 * That difference makes them do much different things internally. runBlocking() has to first initialize the whole coroutines machinery before it could execute a suspendable lambda.
 * coroutineScope() has access to existing coroutine context, so it only schedules the inner lambda to be executed and suspends.

 * Now, suspend fun main() is just a "shortcut" that Kotlin compiler provides to make it easier to initialize coroutines apps.
 * Internally, it creates a bridge between the real main function and your suspendable main function. It generates a separate main() function, which is not suspendable and is the "real" main function.
 * This function initializes a coroutine and uses internal runSuspend() utility to execute your suspend fun main().
 * This is described here: https://github.com/Kotlin/KEEP/blob/master/proposals/enhancing-main-convention.md#implementation-details-on-jvm-1

 * Both methods of starting a coroutine application are very similar and you can choose according to your taste.
 * One notable difference is that runBlocking() creates a dispatcher using the current "main" thread.
 * suspend fun main() also executes using the main thread, but it doesn't specify the dispatcher, so whenever you use e.g. coroutineScope() it will switch to Dispatchers.Default.
 * As a result, your runBlocking() example uses single-threaded dispatcher while your coroutineScope() example uses multi-threaded Dispatchers.Default.
 * However, this difference should not really be taken into account when choosing between both methods. We can very easily switch dispatchers at any time.

https://stackoverflow.com/a/72067310/9133569
 * **/

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