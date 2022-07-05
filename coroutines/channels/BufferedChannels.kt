package coroutines.channels

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// channels by default have unlimited capacity, but we can specify a limit in the Channel constructor, making it a buffered channel. Here we'll limit it to 4 elements capacity.
fun main(){
    runBlocking {
        val channel = Channel<Int>(4)
        val sender = launch {
            //we'll hit the limit of 4 values, then the channel will wait until we take out ( i.e. call receive() ) an element, then put the other 3 elements in the queue
            repeat(10){
                channel.send(it)
                println("Send $it")
            }
        }
        repeat(3){
            delay(1000)
            println("Received ${channel.receive()}")
        }
        sender.cancel()
    }
}