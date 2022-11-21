package coroutines.coroutine_builders

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        println("start of main()")
        // When launch { ... } or  async { ... } are used without parameters, they inherits the context (and
        //thus dispatcher) from the CoroutineScope they are being launched from, In this case, they inherits the context of main()'s
        // runBlocking coroutine, which runs in the main thread by default.

        // All  coroutines below are executed in the runBlocking scope, which is a single-threaded event loop.
        // So this means only a single thread is ever used unless a different context is specified (by either launch(Dispatchers.IO) or withContext(Dispatchers.IO))

        // This function never actually suspend, and instead it blocks the single running thread that launch() associated with their parent CoroutineScope runBlocking's dispatcher, which runs in the main thread by default.
        launch {
            println("launching doBlockingIO's CoroutineScope")
            doBlockingIO()
        }

        // instead, wrap the blocking method in withContext(Dispatchers.IO) to temporarily switch the context for the current coroutine to IO.
        // When you apply withContext(Dispatchers.IO) { ... } around the suspendable code, you'll get concurrency, but of the plain-old Java type, i.e. several threads being blocked in IO operations together.
        launch {
            println("launching doSuspendableIO1's CoroutineScope")
            doSuspendableIO1()
        }

        // or switch the context inside launch()'s CoroutineContext's parameter by supplying an IO CoroutineDispatcher.
        launch(Dispatchers.IO) {
            println("launching doSuspendableIO2's CoroutineScope")
            doSuspendableIO2()
        }

        println("end of main()")
    }
}

 fun doBlockingIO() {
    Thread.sleep(3000) // pretend we are doing something useful here
    println("doBlockingIO() finishes")
}

suspend fun doSuspendableIO1() =  withContext(Dispatchers.IO){
    Thread.sleep(3000) // pretend we are doing something useful here
    println("doSuspendableIO1() finishes")
}

fun doSuspendableIO2() {
    Thread.sleep(3000) // pretend we are doing something useful here
    println("doSuspendableIO2() finishes")
}

/**
 * Coroutines' builders don't magically turn your blocking network API into non-blocking, the context still needs to be switched to IO, since by default Coroutines' builders run on main thread.
 * Use launch(Dispatchers.IO) { ... } or async(Dispatchers.IO) { ... } to run blocking tasks in an I/O thread pool.
 * Just note that this doesn't do much more than the plain-old executorService.execute(blockingTask) and executorService.submit(blockingTask).
 * But with Coroutine builders, it's a bit more convenient because they use a pre-constructed global thread pool, rather than you having to define thread pools with Executor classes.
 * **/

/**
 * Note that when you don't specify a Dispatcher, or explicitly call Dispatcher.Main, then multiple coroutines can run on a single thread,
 * but they are never executed in parallel, rather they actually get executed concurrently/asynchronously.
 * Such coroutines *may* appear to be running in parallel because of switching from one coroutine to another occurring very fast, giving you the allusion of parallelism.
 * */