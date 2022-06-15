package coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

// A pipeline is a pattern where one coroutine is producing, possibly infinite, stream of values.
// And another coroutine or coroutines are consuming that stream, doing some processing, and producing some other results. In the example below, the numbers are just squared
fun main() {

    runBlocking{
        val numbers = produceInfiniteNumbers()
        val squares = square(numbers)

        for (i in 1..5)
            println(squares.receive())
        println("Done!")
        coroutineContext.cancelChildren() //cancel the two channel coroutines so that the process is finished
    }
}

fun CoroutineScope.produceInfiniteNumbers() = produce {
    var x = 1
    while(true)
        send(x++)
}

fun CoroutineScope.square(channel: ReceiveChannel<Int>) = produce {
    for (x in channel)
    send(x*x)
}