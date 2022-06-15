package coroutines.channels

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

// creating channels and returning them without explicitly making Channel objects can be done with the produce {} function
fun main(){
    runBlocking {
        val channel = produce {
            for (x in 1..5)
                send(x*x)
        }

        channel.consumeEach { println(it) }

//        for (y in channel)
//            println(y)
    }
}