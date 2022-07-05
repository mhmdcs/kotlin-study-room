package coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Backpressure refers to the situation where a system is receiving data at a higher rate than it can process during a temporary load spike.
// In such cases, software engineers must handle back pressure gracefully. Thankfully, Kotlin's flows allow us to handle such cases with the .buffer() operator.

//upstream
fun dataSpikedFlow(): Flow<Int> {
    return flow {
        for (i in 1..10){
            delay(500) // data is being produced very fast
            emit(i)
        }
    }
}

//downstream
fun main(){
    runBlocking{
        val time = measureTimeMillis {
        dataSpikedFlow().buffer().collect{
            delay(1000) // data is processed slowly with a 1 second delay
            println("Value is  $it")
        }
        }
        println("collected in: $time ms") // Without the buffer operator, it takes 15~ seconds to collect. With the buffer, it only takes us 10~ seconds
    }
}

