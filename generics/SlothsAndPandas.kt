package generics

open class Mammal(val name: String){
    fun sleep() {}
    fun eat() {}
}

data class Sloth(val slothName: String, val isTwoFingered: Boolean): Mammal(slothName)

data class Panda(val pandaName: String): Mammal(pandaName)

fun feedCrew(crew: List<Mammal>){
    crew.forEach {
        it.eat()
        println("${it.name} ate some rad leaves!")
    }
}

fun main(){
    val sloth: Sloth

    val slothCrew = listOf(
        Sloth("Jerry", false),
        Sloth("Bae", true),
        Sloth("Peter", false)
    )

    feedCrew(slothCrew)

    val slothList: List<Sloth> = listOf()
    val slothList2 = listOf<Sloth>()

    val pandaCrew = listOf(
        Panda("Harry"),
        Panda("Mary"),
        Panda("Gwen")
    )

    feedCrew(pandaCrew)

    val squaaaaad =  listOf(
        Sloth("Jerry", false),
        Sloth("Bae", true),
        Sloth("Peter", false),
        Panda("Harry"),
        Panda("Mary"),
        Panda("Gwen")
    )

    //sort the names in the last alphabetically by taking the first char of the name and turning it to int, then using the Comparator object to sort the list
    val compareByName = Comparator {a: Mammal, b: Mammal ->
        a.name.first().toInt() - b.name.first().toInt()
    }

    //iterate through the results, and using Kotlin's reflection we can call ::println instead of println(it)
    squaaaaad.sortedWith(compareByName).forEach(::println)

//    squaaaaad.sortedWith(compareByName).forEach { (println(it)) }


}