package coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Channels are conceptually similar to queues. You put in items in a queue with send() and take them out with receive()
// Remember how queues work: The element you take out gets removed from the queue.
fun main() {
    runBlocking {
        val channel = Channel<Int>()

        launch {
            for (x in 1..5)
                channel.send(x * x)
            channel.close()
        }

        for (x in 1..5)
            println(channel.receive())

//        for(i in channel)
//            println(i)
    }
}