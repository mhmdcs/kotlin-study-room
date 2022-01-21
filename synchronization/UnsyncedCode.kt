package synchronization

import kotlinx.coroutines.*

//full article here https://proandroiddev.com/synchronization-and-thread-safety-techniques-in-java-and-kotlin-f63506370e6d
//to make a code thread-safe (i.e. making it safely accessible by multiple threads without causing unexpected behavior)
//we need to add a synchronization mechanism to it. So here's a basic example of why synchronization is needed.

/*In this example, we are launching 1000 coroutines (If you don’t know what they are, just think about them as light-weight
 threads) on 4 threads, and each of them increment the global value sharedCounter 1000 times, so the final
 value of the sharedCounter should be 1000000, but it hardly ever is, unless you get very lucky, because sharedCounter++
 gets accessed by multiple threads at the same time, who keep incrementing it all at once, they're out of sync.
* */

/*Imagine there’s one room (sharedCounter) and many people (threads) want to use it, but only one person is allowed at a time.
 There can only be one person in the room, but this room has a door, which means people can open the door and come in to use the room.
 Which means that this room needs a lock! That's what's happening in the code below. */

/*What is happening here is that one thread gets the current value of sharedCounter, and since we are in a multi-threaded world,
 another thread jumps in, and tries to get the current value of sharedCounter, resulting in both of threads getting the same value!
 So each of them increment the value of sharedCounter by 1, and store it to this same value.

 What is also happening is that a thread increments the value, but before storing it, another thread increments it and stores it to
 the sharedCounter value, and when the first thread tries to store it, it stores to an older version of the sharedCounter.
 This is why the final value is not what we expect, it's out of sync in so many ways.
 */
fun main() = runBlocking {
    var sharedCounter = 0
    val scope = CoroutineScope(newFixedThreadPoolContext(4, "synchronizationPool")) // We want our code to run on 4 threads
    scope.launch {
        val coroutines = 1.rangeTo(1000).map { //create 1000 coroutines (light-weight threads).
            launch {
                for(i in 1..1000){ // and in each of them, increment the sharedCounter 1000 times.
                    sharedCounter++
                }
            }
        }

        coroutines.forEach {
                coroutineJob ->
            coroutineJob.join() // wait for all coroutines to finish their jobs.
        }
    }.join()

    println("The number of shared counter should be 1000000, but actually is $sharedCounter")
}