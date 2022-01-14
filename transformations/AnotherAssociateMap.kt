package transformations

fun main() {
    data class FullName (val firstName: String, val lastName: String)

    fun parseFullName(fullName: String): FullName {
        val nameParts = fullName.split(" ")
        if (nameParts.size == 2) {
            return FullName(nameParts[0], nameParts[1])
        } else throw Exception("Wrong name format")
    }

    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
//    val mapOfNames = names.associate { it.first() to it.length }
    val mapOfNames = names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } }

    println(mapOfNames)
}
//the nested lambda above can be read as the following
//val mapOfNames = names.associate { name ->
//    val nameObject = parseFullName(name)
//    nameObject.lastName to nameObject.firstName
//}