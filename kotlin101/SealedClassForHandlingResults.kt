package kotlin101

//Sealed classes are, in a sense, similar to Enum classes. Each enum constant exists only as a single instance (they are implicitly static). Whereas a subclass of a sealed class can have multiple instances, each with their own state.
//Sealed class is abstract by default i.e. it cannot be instantiated. Sealed classes can have abstract members.
//A sealed class can have subclasses, but all of them must be declared in the same package as the sealed class itself. We usually define the subclasses as nested classes inside the sealed class's body for clarity and readability since it forces us to call the sealed class explicitly before calling its subclasses.
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