package InterfaceVsAbstract

//Interfaces can have properties (and hold states through bad practice hacks), but without backing fields
//more in this article here https://blog.kotlin-academy.com/abstract-class-vs-interface-in-kotlin-5ab8697c3a14

//Property in Kotlin represents an abstraction of getter (val) or getter and setter (var).
//By default, they have fields used under the hood.

//Properties are just accessors (getters and setters), and so they can be present on interfaces as long as they do not have any actual values:
interface Animal2 {
    val name: String
    val type: String

    val fullName: String
        get() = "$name of type $type"
}

class Fox2: Animal2 {
    override val type: String = "Tibetan sand fox"
    override val name: String = "Sox"
}

fun main() {
    val fox = Fox2()
    print(fox.fullName) // Sox of type Tibetan sand fox
}