package transformations

fun main() {
//associateWith (elements in a list are transformed into keys in a map)
    println("associateWith example")
    var order = 1
    val numbersWith = listOf("one", "two", "three", "four", "five", "four")
    val mapOfNumbers = numbersWith.associateWith { order++ }
    println(mapOfNumbers)

    //associateBy (elements in a list are transformed into values in a map)
    println("associateBy example")
    val numbersBy = listOf("one", "two", "three", "four", "five")
    println(numbersBy.associateBy{ it.first().toUpperCase() })
    println(numbersBy.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))

}