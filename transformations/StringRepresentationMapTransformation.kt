package transformations

fun main() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers)

    println()
    println("Showcasing joinToString() string representation transformation extension function")
    println(numbers.joinToString())

    println()
    println("Showcasing joinTo() string representation transformation extension function")
    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString)

    println()
    println("Showcasing joinToString() with custom string representation, specifying its parameters in function arguments like separator, prefix, and postfix, and more")
    println(numbers.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    println()
    //Finally, to customize the representation of elements themselves, provide the transform function.
    println(numbers.joinToString { "Element: ${it.toUpperCase()}"})

    println()
    //For bigger collections, you may want to specify the limit – a number of elements that will be included into result. If the collection size exceeds the limit, all the other elements will be replaced with a single value of the truncated argument.
    val largeCollection = (1..100).toList()
    println(largeCollection.joinToString(limit = 10, truncated = "<...>"))

}