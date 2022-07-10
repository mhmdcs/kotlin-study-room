package functions

// Higher-order functions are functions that take other functions as parameters, or return functions
// The function that gets passed in as an argument is called a callback method
fun main() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas are code blocks enclosed in curly braces. They represent the callback functions to be implemented and passed inside the higher-order function. Fold() is the higher order function and thee lambda {} is the callback function that's being passed in.
    items.fold(0) {
        // When a lambda has parameters, they go first, followed by '->'
            acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // The last expression in a lambda is considered the return value:
        result
    }

    // Parameter types in a lambda are optional if they can be inferred:
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

    // "Function references" via the reflection operator double-colons :: can also be used for higher-order function calls:
    val product = items.fold(1, Int::times)

    println("joinedToString = $joinedToString")
    println("product = $product")
}


//this implementation of the functional programming idiom "fold" higher-order function is from the kotlinlang.org documentation
//the implementation in the source code for the actual fold() higher-order extension function is a bit different, compare and study them!
fun <T, R> Collection<T>.fold2(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}