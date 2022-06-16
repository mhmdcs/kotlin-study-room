package coroutines.misc_coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {

    launch {
        println("Start of sub 2nd coroutine from delegated_properties.main thread : Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .launch builder)")
        withContext(Dispatchers.Default){println("In sub 2nd coroutine from defaultDispatcher thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .launch builder)")}
        withContext(Dispatchers.IO){println("In sub 2nd coroutine from ioDispatcher thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .launch builder)")}}

    println("Start of 1st coroutine from delegated_properties.main thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .runBlocking builder)")
    withContext(Dispatchers.IO) {println("In 1st coroutine from ioDispatcher thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .runBlocking builder)")
        delay(1500)
        println("In 1st coroutine from delayed ioDispatcher thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .runBlocking builder)")}
    println("End of 1st coroutine from delegated_properties.main thread: Thread name: ${Thread.currentThread().name} Thread id: ${Thread.currentThread().getId()} -- (via .runBlocking builder)")
}

//fun delegated_properties.main() {println("No coroutines!: ${Thread.currentThread().name} ${Thread.currentThread().getId()}")}