package coroutines.misc_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//https://medium.com/microsoft-mobile-engineering/kotlin-coroutines-1c8e009cb711#:~:text=Now%20we%20all%20agree%20that,will%20continue%20after%20that%201ms.

fun main(){
    creating10kThread()
    creating100KCoroutines()
}

/**Creating 10k Threads, each thread is sleeping for 1ms.**/
fun creating10kThread() {
    val time = measureTimeMillis {
        for(i in 1..10_000) {
            Thread(Runnable {
                Thread.sleep(1)
            }).run()
        }
    }
    println("${time.toDouble()/1000} seconds to create 10k Threads")
}

/**Creating 100k Coroutines (10 times more!), and increasing the delay to 10 seconds (10000 times higher!).*/
fun creating100KCoroutines(){
    val time = measureTimeMillis {
        runBlocking {
            for(i in 1..100_000) {
                launch {
                    delay(10_000L)
                }
            }
        }
    }
    println("${time.toDouble()/1000} seconds to create 100k Coroutines")
}


//Note that creation of coroutines is only faster than creation of threads when the quantity is high
//in other words, if you test creating 10 coroutines and 10 threads, creation of threads would be faster.
//which basically says, the more and more coroutines you have in your program, the greater the benefits reaped. Don't be shy of launching "too many" coroutines.