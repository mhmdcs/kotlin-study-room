package delegates

// achieving decorator pattern via class delegation using Kotlin's `by` keyword:

// When you need to add some behavior to a class you have two choices:
// 1 - Extend it and create a subclass.
// 2 - Use the decorator pattern to decorate the class with new functionality.
// When the class that you want to add some extra functions to is a non-extensible class for whatever reason, the only way to accomplish this is the second one.

// The decorator pattern does 3 things:
// 1- Creates a new class that has a property that is an instance of the class we want to decorate.
// 2 - Implement the un-inheritable class's interface and override all its needed methods.
// 3 - call the decorated object's methods as if they were super calls, then we add the extra functionality to them that we want.
// This could've been a lot of boilerplate code. Fortunately for us, Kotlin makes it really easy to create decorators/wrappers using
// its built-in class delegation support with the use of the `by` keyword.

// below, we decorate a Pikachu object with functions that give it a new attack, i.e. new functionality on top of its already existing ones.

// the interface that our un-inheritable class implements
interface Pokemon {
    fun attack()
}

// our soon-too-be-decorated, un-inheritable class (imagine it's un-inheritable because it's from a final class we do not own, e.g. a class from some library)
class Pikachu : Pokemon {
    override fun attack() {
        println("Pikachu has some complex implementations")
        println("Thunder Shock!")
    }
}

// our decorator/wrapper class that indirectly "extends" Pikachu by delegating the responsibility
// of implementing the Pokemon's interface attack() method to the un-inheritable Pikachu object,
// while we add some extra functionality on top to our own EvolvedPikachu class.
class EvolvedPikachu (private val pikachu: Pokemon = Pikachu()) : Pokemon by pikachu{
    override fun attack() {
        pikachu.attack() // imagine this line of code like you're calling the super method of Pikachu ;p
        println("EvolvedPikachu has some extra complex functionalities")
        println("Thunderbolt!")
    }
}
// now if some other method or class required us to pass a Pokemon object in its parameters, we could easily pass in an instance of our EvolvedPikachu class :)

// EvolvedPikachu is exactly the same as EvolvedPikachu2 below, if Pikachu class was actually inheritable, then we could've normally extended it,
// then overridden its attack() method while calling super.attack() in the first line, and we could've achieved the same exact behavior.
// but since Pikachu is un-inheritable due to being a final class, the decorator/delegation pattern came to our rescue :)
//class EvolvedPikachu2: Pikachu(){
//    override fun attack() {
//        super.attack()
//        println("EvolvedPikachu has some extra complex functionalities")
//        println("Thunderbolt!")
//    }
//}

fun main() {
    val evolvedPikachu = EvolvedPikachu()
    evolvedPikachu.attack()

//    val evolvedPikachu2 = EvolvedPikachu2()
//    evolvedPikachu2.attack()
}