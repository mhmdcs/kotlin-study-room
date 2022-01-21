package synchronization

import kotlinx.coroutines.*

/*Synchronized block statements take any object as their lock, and use the lock to execute their code-block. these blocks have
 the advantage of being more fine-grained (as they are more scoped). In the code below, synchronized(this)
 defines `this` (the Incrementer’s instance) as the lock object. Any thread that reaches this point, locks the Incrementer instance,
 then does the work defined in the synchronized block, and then releases the lock.*/

//synchronized block statement

class Incrementer() {
    var sharedCounter: Int = 0
        private set

    fun updateCounterIfNecessary(shouldIActuallyIncrement: Boolean) {
        if (shouldIActuallyIncrement) {
            synchronized(this) {
                //only locks when needed, using the Incrementer`s instance as the lock.
                sharedCounter++
            }
        }
    }
}
fun main() = runBlocking {
    val incrementer = Incrementer()
    val scope = CoroutineScope(newFixedThreadPoolContext(4, "synchronizationPool")) // We want our code to run on 4 threads
    scope.launch {
        val coroutines = 1.rangeTo(1000).map {
            //create 1000 coroutines (light-weight threads).
            launch {
                for (i in 1..1000) { // and in each of them, increment the sharedCounter 1000 times.
                    incrementer.updateCounterIfNecessary(it % 2 == 0)
                }
            }
        }
        coroutines.forEach { coroutineJob ->
            coroutineJob.join() // wait for all coroutines to finish their jobs.
        }
    }.join()
    println("The number of shared counter is ${incrementer.sharedCounter}")
}