package collections

// List and Set both are interfaces. They both extends Collection interface.
// In most programming languages, the delegated_properties.main difference between List and Set is that Set is unordered and contains only unique elements,
// whereas the List is ordered and can contain the duplicate elements in it.

// List is an ordered collection it maintains the insertion order, which means upon displaying the list content it will display the elements in the same order in which they got inserted into the list.
// Set is an unordered collection, it doesn't maintain any order.
// There are few implementations of Set which maintains the order such as LinkedHashSet (It maintains the elements in insertion order).
// In KOTLIN, all the toSet, setOf and mutableSetOf implementations are all implemented with LinkedHashSet. So Sets in Kotlin *do* retain their order just like Lists :)
// Kotlin also provides the function of linkedSetOf to *explicitly* create a LinkedHashSet, but it's unneeded since setOf already does create a LinkedHashSet
fun main(){
    //this is a List collection, it is an ordered collection of the same defined type, we can get its elements by its indices
    val list: List<String?> = listOf("New Jersey", "California", "Texas", "California") //notice that duplicates are inserted in Lists
    println(list[0])
    println(list)

    //this is a Set collection, it is an ordered LinkedHashSet collection of the same defined type, we can get its elements by its indices
    val set: Set<String?> = setOf("New Jersey", "California", "Texas", "New Jersey") //notice that duplicates are NOT inserted in Sets
    println(set.elementAt(0))
    println(set)
}