package functions
//https://kotlinlang.org/docs/reflection.html#function-references

// when you have a named function declared as below, you can call it directly (isOdd(5))
fun isOdd(x: Int) = x % 2 != 0

// same function overloaded by having a different signature (i.e. different parameters)
fun isOdd(s: String) = s == "One" || s == "Three" || s == "Five"

fun main() {

    println(isOdd(5))

    // Alternatively, you can use the function as a *function type* value.
    // That is, pass it as a callback to another function (higher-order function).
    // You could previously do this by passing it as lambda, but you can also pass the function reference with reflection
    // To do so, use the double-colons :: reflection operator

    val numbers = listOf(1, 2, 3)
    println( numbers.filter({isOdd(it)}) ) //isOdd function passed as a lambda callback to the filter() higher-order extension function
    println( numbers.filter(::isOdd) ) //isOdd function passed as a function reference callback using reflection to the filter() higher-order extension function
    //Here ::isOdd is a value of function type (Int) -> Boolean.
    // :: operator can be used with overloaded functions when the expected type is known from the context.
    // when it was used above, it referred to isOdd(x: Int) since the expected type was known from the context of the numbers object


    //Alternatively, you can provide the necessary context by storing the method reference in a variable with an explicitly specified type:
    val predicate: (String) -> Boolean = ::isOdd   //here variable predicate explicitly refers to isOdd(s: String)
    println(predicate("Three"))

}