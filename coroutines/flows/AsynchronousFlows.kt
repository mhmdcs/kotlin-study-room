package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow

fun main(){

    runBlocking{
        // Flows are cold. If they were hot, they would've been immediately executed by just calling sendPrimes() function, but because they're cold, we need to collect (consume) them first to execute them.
        val flow = sendPrimes()
        println("Flows not executed yet..")
        flow.collect{
            println(it)
        }
        println("Flows executed after collecting them")
    }
}

fun sendPrimes(): Flow<Int> = flow {

    val primesList = listOf<Int>(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primesList.forEach {
        delay(it * 100L)
        emit(it)
    }
}