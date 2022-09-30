package functions.FunctionTypesAsCallbacks

// A callback is a function that waits for another function to finish executing before executing itself. It gives us the ability to enforce an order of operations to our code.
// Functions are objects in Kotlin. Because of this, functions can take other functions as arguments, and functions can also be returned by other functions. When a function takes another function as an argument or returns a function as a result, that function is called a higher-order function. Any function that is passed as an argument is called a callback function.

class SomeClass {
    //  doSomething() is a higher-order function that takes in a callback function as an argument
    fun doSomething(callback: () -> Unit) {
        println("I did something")
        callback()
    }
}

fun main() {
    val someClass = SomeClass()

    someClass.doSomething { // inside the lambda here, is the body of the callback function, this block of code be called back after everything inside doSomething() finishes executing, since we call it back at the end of doSomething()
        println("I observed doSomething() inside the SomeClass class and got called back")
    }

    // alternatively
    // store the function type inside a variable that you can later pass in
    val callback = {
        println("I observed doSomething() inside the SomeClass class and got called back")
    }

    // declare a named function that has no arguments and returns nothing i.e. a function that matches the type () -> Unit
    fun callback2(){
        println("I observed doSomething() inside the SomeClass class and got called back")
    }

//    someClass.doSomething(callback) // you can pass in a variable that stores the function type
//    someClass.doSomething(::callback2) // you can pass in a named function as an argument with the :: function reference operator
}