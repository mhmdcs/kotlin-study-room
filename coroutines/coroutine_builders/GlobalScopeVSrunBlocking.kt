package coroutines.coroutine_builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//The main difference is that the runBlocking method blocks the current thread until it executes its entire code, while GlobalScope and coroutineScope just suspend the process, thus releasing the underlying thread for other usages until they can be resumed later.

//delay() is suspending and non-blocking. Basically, delay() has the effect of "waiting" before executing the statement that follows it in the current coroutine. It is also non-blocking meaning that during this wait, the current thread can do something else. It is not like Thread.Sleep() which does block the current thread.

//suspend functions are designed to block current coroutine, not the thread itself, it means that any and all suspend functions should and must run in a background thread. For example the delay() function blocks coroutine for a given time without blocking the current thread and later resumes the coroutine after a specified time you've set passes.

fun main(){
   // globalScopeTest()
    runBlockingTest()
}

fun globalScopeTest(){

    //GlobalScope's lifecycle is tied to the entire application lifecycle, never use this in real apps. Instead, use lifecyleScope and viewmodelScope in Android application's activities/fragments and viewmodels respectively.
    GlobalScope.launch{
    println("Globalscope coroutine launched")
        delay(1000)
        println("Globalscope coroutine ends")
    }
   // Thread.sleep(2000) //since launch is a suspend function, it can block the coroutine and let the current thread do other things, such as executing the println() below, and when that gets executed, our program ends, and since GlobalScope's lifecycle is tied to the entire program, the coroutine ends with it too, and it never gets executed. If we want to execute that coroutine, we need to add some sort of an artificial delay by blocking the current thread with Thread.Sleep(), and it'll give the coroutine more time to  start and finish before the program ends.
    println("I am out of the coroutine-world")
}

fun runBlockingTest(){

    //runBlocking blocks the thread until this entire coroutine inside it executes.
    runBlocking {
        println("runBlocking coroutine launched")
        delay(1000)
        println("runBlocking coroutine ends")
    }
    println("I am out of the coroutine-world")
}