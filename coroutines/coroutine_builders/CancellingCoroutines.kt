package coroutines.coroutine_builders

import kotlinx.coroutines.*
import sun.nio.ch.ThreadPool
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// SupervisorJob() is used instead of Job() to make different child coroutines independent
private val job: CompletableJob = SupervisorJob() // CompletableJob implements Job which implements Element which implements CoroutineContext
private val dispatcher: CoroutineDispatcher = Dispatchers.IO // CoroutineDispatcher implements ContinuationInterceptor which implements Element which implements CoroutineContext
private val exception: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable -> // CoroutineExceptionHandler implements Element which implements CoroutineContext
    println("coroutineContext is $coroutineContext - throwable is $throwable")
}

private val executorService: ExecutorService = Executors.newFixedThreadPool(3) // Creates a fixed-size thread pool with three threads using Java's ExecutorService class, we can convert it into a CoroutineDispatcher via asCoroutineDispatcher()
private val executorServiceCoroutineDispatcher: ExecutorCoroutineDispatcher = executorService.asCoroutineDispatcher() // ExecutorCoroutineDispatcher implements CoroutineDispatcher implements ContinuationInterceptor which implements Element which implements CoroutineContext
val fixedThreadPoolContext: ExecutorCoroutineDispatcher = newFixedThreadPoolContext(2, "NewFixedThreadPoolContext") // Creates a fixed-size thread pool with two threads
val limitedParallelismContext: CoroutineDispatcher = Dispatchers.IO.limitedParallelism(2) // Creates a fixed-size thread pool with two threads
// more details about newFixedThreadPoolContext and limitedParallelism methods at https://stackoverflow.com/q/71972371/9133569

private val scope: CoroutineScope = CoroutineScope(dispatcher + job + exception) // a coroutine scope is a CoroutineScope object that takes in a CoroutineContext, which could be a Job + CoroutineDispatcher since both implement the CoroutineContext interface, or just a Job, or just a Dispatcher, or just a CoroutineExceptionHandler.

fun main() {
    System.setProperty(IO_PARALLELISM_PROPERTY_NAME, 1000.toString()) // To increase coroutine's Dispatchers.IO size from 64 threads to whatever you want, declare this before you use Dispatchers.IO for the first time (preferably in Application class in Android), and your IO_PARALLELISM_PROPERTY_NAME property change will be applied. Dispatchers.IO is an app-wide singleton, if a library/dependency you use uses coroutines, it will still use the same Coroutine library as your own code, and therefore the same thread pool. more at https://stackoverflow.com/a/58490967/9133569

    start()
    stop()
}

fun start() {
    scope.launch {
        withContext(Dispatchers.IO) {
            // start a blocking operation
            Thread.sleep(2000)
        }
    }
}

fun stop() {
    scope.cancel() // we could also call job.cancel(), both do the same thing, they cancel the coroutine, including its child coroutines.
}


/**
 * SupervisorJob() creates a supervisor job object in an active state. Children of a supervisor job can fail independently of each other.
 * A failure or cancellation of a child does not cause the supervisor job to fail and does not affect its other children, so a supervisor can implement a custom policy for handling failures of its children
 *
 *
 * Job() creates a job object in an active state. A failure of any child of this job immediately causes this job to fail, too, and cancels the rest of its children.
 * To handle children failure independently of each other use SupervisorJob instead.
 * If parent job is specified, then this job becomes a child job of its parent and is cancelled when its parent fails or is cancelled. All this job's children are cancelled in this case, too. The invocation of cancel with exception (other than CancellationException) on this job also cancels parent.
 * */

// Using viewModelScope and lifecycleScope is convenient and recommended if we do not need any special context as a part of our scope (like CoroutineExceptionHandler).
//This is why this approach is chosen by many (perhaps most) Android applications.