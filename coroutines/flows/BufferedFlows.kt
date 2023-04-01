package coroutines.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Backpressure refers to the situation where a system is able to handle receiving data at a higher rate than it can process during a temporary load spike.
// In such cases, software engineers must handle the implementation of back pressure gracefully.
// Thankfully, Kotlin's flows support backpressure support automatically. If the flow emits values faster than it can be collected,
// then it's not a problem, because collect() is slower than emit(), collector will always slow down the emitter, this is all possible thanks to the nature of suspending functions, which collect() is one.

// Flow API also allow us to improve the performance of backpressure streams with the buffer() operator, instead of emitting then collecting, emitting then collecting, emitting then collecting, etc, while each collection is very slow,
// what we can do instead is use the buffer() operator to make the emission of flows asynchronous rather than sequential, flows emission and collection by default are run on the same coroutine and thus are sequential, but when we the
// buffer() operator, the emission and collection are run on two separate coroutines, making the emission asynchronous, so we instead emit, then collect and still emit at the same time. For a better understanding of this watch Roman
// Elizarov Flow talk  https://www.youtube.com/watch?v=tYcqn48SMT8&t=264s&ab_channel=JetBrainsTV when he starts talking about backpressure and the buffer() operator.

//upstream
fun dataSpikedFlow(): Flow<Int> {
    return flow {
        for (i in 1..10) {
            delay(500) // data is being produced very fast
            emit(i)
        }
    }
}

//downstream
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            dataSpikedFlow().buffer().collect {
                delay(1000) // data is processed slowly with a 1 second delay
                println("Value is  $it")
            }
        }
        println("collected in: $time ms") // Without the buffer operator, it takes 15~ seconds to collect. With the buffer, it only takes us 10~ seconds
    }
}

