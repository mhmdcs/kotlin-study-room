package coroutines.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 *
The power of the Flow API (reactive programming) and thinking of everything as a stream of data:
With coroutines, we can use suspend functions and return a value:
suspend fun foo0(): Response

We wait for a server and over time, we get a response, or instead of a one response, we could have many responses, and return a list of values:
suspend fun foo0(): List<Response>

Let's look at this example where the buildList function does some computations by calling a hypothetical compute() suspend function and adds elements to a list, and then returns that list:

 Read your notes and watch for further context: https://www.youtube.com/watch?v=tYcqn48SMT8&t=264s&ab_channel=JetBrainsTV
 **/

fun main() {
    runBlocking {
        // foo0() returns a list that has elements added to it sequentially, and so if each element takes a long time to be computed, we are forced to wait until the whole list is ready before we can process its elements.
        val list = foo0()
        for (x in list) println(x) // we process the elements in the list

        // foo1() returns immediately with a channel that has elements sent to it asynchronously, and then each element is received asynchronously when we iterate over it with a for-loop, channels allow us to process each element as soon as they're computed.
        val channel = foo1()
        for (x in channel) println(x) // we process the elements in the channel

        val flow = foo2()
        flow.collect {
            println(it)
        }
    }
}

// What happens here? We call foo() from the main() function, and the execution jumps to foo(), foo() computes value "A"  and adds it to the list,
// then computes "B"  and adds it to the list, then computes "C"  and adds it to the list, and then the list gets returned, and then the execution
// goes back to main() function, the main() function was waiting for the result this entire time, and then it iterates over the list and prints it, this whole process was a sequential execution.
suspend fun foo0(): List<Char> = buildList {
    add(compute('A'))
    add(compute('B'))
    add(compute('C'))
}


// The problem with this, if computing those values took a lot of time, we could have started processing them as soon as they were computed,
// but because we were using a list, we had to wait until the whole list of elements was ready to start working on it.

// This is inefficient and adds unnecessary delay in the data processing pipelines, especially since getting data from network takes time,
// we could do better, we could start working on stuff as soon as it's ready instead, but how should we do it?

fun CoroutineScope.foo1(): ReceiveChannel<Char> = produce {
    send(compute('A'))
    send(compute('B'))
    send(compute('C'))
}

fun foo2(): Flow<Char> = flow {
    emit(compute('A'))
    emit(compute('B'))
    emit(compute('C'))
}

suspend fun compute(char: Char): Char {
    delay(1000) // print we are doing long-running computations with each element
    return char
}
