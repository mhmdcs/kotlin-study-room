package collections

//"Map" key-value data structure in Kotlin are called Dictionary in other languages.
//other names for key-value data structures are: Associative Arrays, Hash Tables, or Dictionary (like in C#)


fun main(){

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