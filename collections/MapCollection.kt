package collections

//"Map" data structure in Kotlin are called Dictionary in other languages, like C#.
fun main(){

    //this is a List collection, it is an ordered collection of the same defined type, we can get its elements by their indices
    val list: List<String> = listOf("New Jersey", "California", "Texas")
    println(list[0])

    //this is a Map collection (Dictionary), it is an unordered collection that maps keys to values, a key-value data structure
    val map: Map<String, String> = mapOf(
        "NJ" to "New Jersey",
        "CA" to "California",
        "TX" to "Texas"
      //key  to  value
    )
    println(map["TX"])

    println(map.keys)
    println(map.values)
    println(map)
}