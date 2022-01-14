package transformations

fun main() {

    //flatten() function returns a single List of all the elements of the nested collections.
    println("flatten() transformation extension function example")
    val set_Of_Lists_Of_Numbers = setOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(1, 2))
    val flattened_List_Of_Set_Of_Lists_Of_Numbers = set_Of_Lists_Of_Numbers.flatten()
    println(flattened_List_Of_Set_Of_Lists_Of_Numbers)


    //flatMap() behaves as a successive call of map() (with a collection as a mapping result) and flatten().
    //Meaning, it provides a flexible way to process nested collections. It takes a function that maps a collection element to another collection.
    //As a result, flatMap() returns a single list of its return values on all the elements.
    println("flatMap() transformation extension function example")
    data class StringContainer(val values: List<String>)
    val containers = listOf(
        StringContainer(listOf("one", "two", "three")),
        StringContainer(listOf("four", "five", "six")),
        StringContainer(listOf("seven", "eight"))
    )
    val flatMappedContainers = containers.flatMap { it.values }

    println(containers)
    println(flatMappedContainers)
}