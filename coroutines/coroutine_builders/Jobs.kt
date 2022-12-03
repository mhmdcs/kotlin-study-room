package coroutines.coroutine_builders

import kotlinx.coroutines.*

fun main(){
    jobCancellation()
}

// a coroutine Job object is cancellable thing with its own lifecycle. Coroutine Jobs are created with the launch{} builder
// coroutine Jobs give us a "handle" on coroutines so we can perform specific operations on them, such as join(), cancel(), and start()
// when a coroutine Job is cancelled, all of its parent and children jobs are cancelled too.


fun ourFirstJob(){
    runBlocking{
        val job = launch {
            println("This is our first Job")
        }
    }
}


fun multipleJobs(){
    runBlocking {
       val job1 = launch {
            println("Job1 created")
           delay(3000)
        }

        val job2 = launch {
           // job1.join() //if this is uncommented, what it will do is that it'll suspend this coroutine until the specified job (job1 in this case) is finished. Thus creating job1 and calling its invokeOnCompletion(), then creating job2 and calling its invokeOnCompletion()
            println("Job2 created")
            delay(2000)
        }

        // invokeOnCompletion() gets called after a job is finished. Since job1 takes 3 seconds until it's completed and finished
        // that means that job2 will be created and job2.invokeOnCompletion will be called before job1.invokeOnCompletion does
        job1.invokeOnCompletion {
            println("Job1 finished")
        }

        job2.invokeOnCompletion {
            println("Job2 finished")
        }
    }
}

fun jobsHierarchy(){
    // since the parent Job doesn't complete until ALL of its children Jobs are completed first, parentJob.invokeOnCompletion() will only be called after childJob completes.
    runBlocking{
       val parentJob = launch {
            println("Parent Job created")

           val childJob = launch {
               println("Child Job created")
               delay(2000)
           }
           childJob.invokeOnCompletion{
               println("Child Job finished")
           }
        }

        parentJob.invokeOnCompletion{
            println("Parent Job finished")
        }
        println("End of runBlocking scope")
    }
}

// when a parent Job is cancelled, all of its child Jobs get cancelled with it, when a child Job gets cancelled, its parent Job and all other jobs in the same hierarchy will be cancelled too.
fun jobCancellation(){
    runBlocking{
        val parentJob = launch {
            println("Parent job created")

            val childJob1 = launch {
                println("Child Job created")
                cancel()
                delay(10_000) //won't get executed
                println("The delay and this print function will never be executed since the Child Job got cancelled") //won't get executed
            }

            childJob1.invokeOnCompletion{
                println("ChildJob1 finished")
            }

            println("End of parentJob's launch scope")
        }

        parentJob.invokeOnCompletion{
            println("Parent Job finished")
        }

        println("End of runBlocking scope")
    }
}