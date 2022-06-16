package coroutines.misc_coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
        launch{
        val user = fetchUser() // fetch on IO thread
        showUser(user) // back on UI thread
        println("Back on transformations.kotlin101.games.coroutines.delegated_properties.main thread during coroutine -- Thread name: ${Thread.currentThread().name} Thread  id: ${Thread.currentThread().id}")
        }
    //"-- Thread name: ${Thread.currentThread().name} Thread  id: ${Thread.currentThread().id}"
    println("I need to be printed first! -- Thread name: ${Thread.currentThread().name} Thread  id: ${Thread.currentThread().id}")
}

//suspend fun coroutines.fetchUser(): String = withContext(Dispatchers.IO) {
//  //delay(3000) delay is a suspend function that doesn't block the current thread
//    Thread.sleep(3000) //Thread.sleep is a function that blocks the  current thread, use  it to simulate a delay
//    return@withContext "Mohammed -- Thread name: ${Thread.currentThread().name} Thread  id: ${Thread.currentThread().id}"
//}

 fun fetchUser(): String {
    Thread.sleep(3000)
    return "Mohammed -- Thread name: ${Thread.currentThread().name} Thread  id: ${Thread.currentThread().id}"
}

fun showUser(user: String) {
    println(user)
}