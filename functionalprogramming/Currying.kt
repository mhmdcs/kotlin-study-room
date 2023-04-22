package functionalprogramming

// Currying is the transformation of a function with multiple arguments into a sequence of single-argument functions.

/**
 * Currying is a common technique in functional programming.
 * It allows transforming a given function that takes multiple arguments into a sequence of functions, each having a single argument.
 * Each of the resulting functions handles one argument of the original (uncurried) function and returns another function.
 * So, currying is a way to translate a function that takes a number of arguments into a chain of functions that each take a single argument.
 * This may sound confusing, so let's look at an example to see how simple it really is:
 * **/

fun main() {

    // currying version 1: currying by storing lambdas inside of a variable.
    val subtract: (Int) -> (Int) -> Int = { x: Int -> { y: Int -> x - y } }

    println(subtract(50)(8)) // using a curried function

    println(subtract(50,8)) // using normal function
}

// normal function that accepts two parameters
fun subtract(x: Int, y: Int): Int {
    return x - y
}

// currying version 4: returning an anonymous function
//fun subtract(x: Int): (Int) -> Int {
//    return fun(y: Int): Int {
//        return x - y
//    }
//}


// currying version 3: returning an anonymous function as a single-expression with = sign
//fun subtract(x: Int): (Int) -> Int = fun(y: Int): Int {
//    return x - y
//}

// currying version 2: returning a lambda as a single-expression with = sign
//fun subtract(x: Int): (Int) -> Int = { y: Int -> x - y }
