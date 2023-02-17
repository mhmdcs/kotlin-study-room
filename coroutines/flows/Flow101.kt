package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*

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

        // foo3() returns immediately with a flow that has elements emitted to it asynchronously, and then each element is collected asynchronously when we call the collect() operator on it, flows allow us to process each element as soon as they're computed.
        // because flows are a cold stream, unlike channels which are hot streams, if an exception is thrown or a condition is checked between calling foo3() and collecting the flow, nothing bad happens, the program doesn't hang, because flows are cold, the code
        // inside the producing builder flow {} *only* gets executed after we start collecting. Unlike channels which are hot like foo1(), which immediately starts producing data to the channel as soon as we call it.
        val flow = foo3()
        flow.collect {
            println(it)
        }

        // demonstrating flow operators on the foo2() flow,
        foo2().onStart { // we use the onStart() operator to say what we want to happen before we start collecting
            delay(3000) // we say, before we start collecting, wait for 3 seconds
            emit('X') // we say, before we start collecting, emit X first
        }.onEach { // we use the onEach() operator to say what we want to happen to each value before the values of the upstream are emitted to the downstream
            println(it.lowercaseChar())  // we say, on each value in the flow, transform it to lower case
        }.catch { // we use the catch() operator to say what we want to happen when an exception is thrown in the flow
            emit('#') // we say, when the flow throws an exception, emit #
        }.collect { // we use the collect() terminal operator to start collecting the flow and thus signal the flow {} builder to begin executing
            println("$it!") // we say, on each value collection on the value, print itt
        }

        // the launchIn() terminal operator combined with the onEach() operator is just a shorter, less indented, way of launching a new coroutine to collect a flow.
        foo3().onEach {
            println("$it")
        }.launchIn(CoroutineScope(Dispatchers.IO))

        // same as the above, but with three indentations instead of two, too many indentations make code hard to understand and reason with :)
        CoroutineScope(Dispatchers.IO).launch {
            foo3().collect {
                println("$it")
            }
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
    send(compute('a'))
    send(compute('b'))
    send(compute('c'))
}

fun foo2(): Flow<Char> = flow {
    emit('A')
    emit('B')
    emit('C')
}

// foo3() is just like foo2(), but in foo3() we demonstrate how declarative the Flow API is, first we use the flowOf() builder,
// then we transform the flow's values with the map() operator which we feed to it our asynchronous long-running compute() to produce a new flow, then we use the flowOn() operator to tell the compiler to run the above flow on a particular dispatcher.
fun foo3(): Flow<Char> =
    flowOf('D', 'E', 'F').map {
        compute(it)
    }.flowOn(Dispatchers.Default)


suspend fun compute(char: Char): Char {
    delay(1000) // pretend we are doing long-running computations with each element
    return char
}
