package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow

fun main(){

    //In coroutines, suspend functions that return only a single value, while flow is a type that can emit multiple values sequentially.

    runBlocking{
        // in cold streams, all the data is produced inside the stream. Whereas in hot streams, all the data is produced outside the stream (This data can exist without the stream and, generally, it comes from external components).
        // Flows are cold. If they were hot, they would've been immediately executed by just calling sendPrimes() function, but because they're cold, we need to collect (consume) them first to execute them.
        // Cold flows are created on-demand and emit data only when they’re being observed. Hot flows are always active and can emit data regardless of whether or not they’re being observed. Flows are cold. Whereas StateFlows, SharedFlows, and Channels are hot.
        val flow = sendPrimes()
        println("Flows not executed yet..")
        flow.collect(object: FlowCollector<Int> {
            override suspend fun emit(value: Int) {
                println(value) // the downstream
            }
        })
        println("Flows executed after collecting them")

        // The signature (parameter) of the flow {}  builder uses a FlowCollector interface as a receiver, this helps us so we can emit directly from inside the body of the flow {} builder.
        // Now you may ask, how do we even get that FlowCollector instance? What happens is that an instance of FlowCollector is created based on the lambda passed to collect {}, and this very instance is then passed to the flow {} body
        /**
        flow.collect {
        println(it) // the downstream
        }

        is equal to:

        flow.collect(object: FlowCollector<Int> {
        override suspend fun emit(value: Int) {
        println(value) // the downstream
        }
        })
         **/
    }
}

fun sendPrimes(): Flow<Int> = flow {

    val primesList = listOf<Int>(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primesList.forEach {
        delay(it * 100L)
        emit(it) // the upstream
    }
}