package synchronization

import kotlinx.coroutines.*

//In order to fix this issue, we need to synchronize the work on this value.
//There are multiple ways to achieve this in Kotlin, either by @Synchronized annotation or synchronized() function.

//@Synchronized annotation

/*As you can see, the output value is now always correct. In the room scenario, @Synchronized resembles a lock on the door that
 has only one key that people must use to either lock or unlock the door. So when one person (a thread) goes in to use the room,
  no one else can enter unless the person unlocks the door and gives the key to the new person.*/

var sharedCounter = 0
@Synchronized fun updateCounter(){
    sharedCounter++
}

fun main() = runBlocking {
    val scope = CoroutineScope(newFixedThreadPoolContext(4, "synchronizationPool")) // We want our code to run on 4 threads
    scope.launch {
        val coroutines = 1.rangeTo(1000).map { //create 1000 coroutines (light-weight threads).
            launch {
                for(i in 1..1000){ // and in each of them, increment the sharedCounter 1000 times.
                    updateCounter() // call the newly created function that is now synchronized
                }
            }
        }

        coroutines.forEach {
                coroutineJob ->
            coroutineJob.join() // wait for all coroutines to finish their jobs.
        }
    }.join()

    println("The number of shared counter is $sharedCounter")
}