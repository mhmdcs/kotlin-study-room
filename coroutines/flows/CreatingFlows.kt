package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

// DOWNSTREAMS:
fun main (){

    runBlocking {

    sendNumbers().collect {
        println("Received Numbers: $it")
    }

    sendNumbers2().collect {
        println("Received Numbers2: $it")
    }

        sendNumbers3().collect() {
            println("Received Numbers3: $it")
        }

    }
}

// UPSTREAMS:

// generating flows and emitting them the normal way
fun sendNumbers() = flow() {
    for(i in 1..5)
        emit(i)
}

// generating flows by converting a List object into a Flow<Int> object and emitting them implicitly
fun sendNumbers2() = listOf(1,2,3).asFlow()

// generating flows from a number of parameters through vararg and emitting them implicitly
fun sendNumbers3() = flowOf("One", "Two", "Three")