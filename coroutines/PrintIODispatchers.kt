package coroutines

import kotlin.system.*
import kotlinx.coroutines.*

//create and print Dispatchers.IO threads

fun main() {

    repeat(500) {
        CoroutineScope(Dispatchers.IO).launch {
            println(Thread.currentThread().name)
        }
    }

}

//creating and printing Dispatchers.IO threads