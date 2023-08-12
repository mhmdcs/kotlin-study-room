package coroutines.threads

import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// Executor and ExecutorService are higher-level abstractions over Threads that provide a more convenient and powerful way to manage and control concurrent execution.
private val executorService: ExecutorService = Executors.newFixedThreadPool(5) // Creates an ExecutorService with a fixed-size thread pool with five threads using Java's ExecutorService class, we can convert it into a CoroutineDispatcher via asCoroutineDispatcher()
private val executorServiceCoroutineDispatcher: ExecutorCoroutineDispatcher = executorService.asCoroutineDispatcher() // ExecutorCoroutineDispatcher implements CoroutineDispatcher implements ContinuationInterceptor which implements Element which implements CoroutineContext
// we could later (if we wanted) supply executorServiceCoroutineDispatcher to the constructor of a CoroutineScope since executorServiceCoroutineDispatcher is a CoroutineContext.

fun main() {
    println("start of main() function")
    val runnable = Runnable {
        println("start of runnable")
        Thread.sleep(3000) // some long running, blocking task
        println("end of runnable")
    }
    println("calling the runnable..")
    executorService.execute(runnable) // or you could also call threadPool.submit(runnable) instead

    executorService.execute {
        // execute another Runnable task
    }

    executorService.submit {
        // execute ANOTHER Runnable task
    }

    // difference between execute() and submit()
    // execute() runs the task and forget about it, it is like Coroutines' launch() builder.
    // submit() returns a future, it is like Coroutines' async() builder.
    // If you check the source code, you will see that submit() is sort of a wrapper on execute()

    executorService.shutdown()
    println("end of main() function")
}

/**
 * In Java, threads are mapped to system-level threads, which are the operating system's resources. If we create threads uncontrollably, we may run out of these resources quickly.

 * The operating system does the context switching between threads as well — in order to emulate parallelism. A simplistic view is that the more threads we spawn, the less time each thread spends doing actual work.

 * The Thread Pool pattern helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits.
 * We use Java's Executor to create thread pools since it's the standard way to create and manage thread pools in Java, and while you can create a thread pool class manually using just Thread, Queue and List objects, doing so would require a significant amount of custom code to manage the lifecycle, synchronization, task queuing, and other concerns that are handled automatically by the executor framework.

 * When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks.
 * **/

/***
 * Use Cases for Thread Pools:
 * Thread pools are often used in server applications to improve performance by creating a thread pool (with a max threshold) that can be used to service requests on demand, rather than creating a new thread for each request.

 * For example, a web server takes advantage of thread pool to serve requests. When a new request arrives, the web server can create a new thread to handle that request. By using a thread pool, the web server can ensure that there are always enough threads available to handle incoming requests.
 */

/**
 * When Not to Use a Thread Pool:
 * If your application does not handle many threads, you can avoid using a thread pool. Creating and destroying threads takes time, so using a thread pool in this situation would just add to the overhead with no appreciable benefit. Additionally, a thread pool itself consumes resources.
 *
 * Another situation where a developer would not want to use a thread pool is if your application requires threads that perform unrelated actions. For example, while one thread handles user events, another executes business logic and yet another thread prints data.
 *
 * Programmers should not use a thread pool if their application is going to be blocked for long periods of time. You should not use thread pools in this case, because, if there are too many blocked threads, the tasks will not start at all.
 * **/