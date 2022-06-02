package random_misc

import java.io.IOException

sealed class Result {
    object Loading : Result()
    data class Success(val successMessage: String) : Result()
    data class Error(val errorMessage: Throwable?) : Result()
}

fun observeResult(result: Result) = when(result) {
    Result.Loading -> println("Loading...Please wait.")
    is Result.Success -> println(result.successMessage)
    is Result.Error -> println(result.errorMessage)
}

fun main() {
    observeResult(Result.Loading)
    observeResult(Result.Success("The Ui has returned a successful result"))
    //observeResult(Result.Error(IOException()))
}


//https://stackoverflow.com/questions/68774084/how-to-use-sealed-classes-in-android-using-kotlin