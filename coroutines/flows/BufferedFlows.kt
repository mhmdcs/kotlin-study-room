package coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// Backpressure refers to the situation where a system is receiving data at a higher rate than it can process during a temporary load spike.
// In such cases, software engineers must handle back pressure gracefully. Thankfully, Kotlin's flows allow us to handle such cases with the .buffer() operator.

fun main(){
    runBlocking{
        dataSpikedFlow().collect{
            delay(2000) // data is processed slowly with a 2 seconds delay
            println("Value is  $it")
        }
    }
}


fun dataSpikedFlow(): Flow<Int> {
    return flow {
        for (i in 1..10){
            delay(300) // data is being produced very fast
            emit(i)
        }
    }
}
