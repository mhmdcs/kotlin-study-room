package InterfaceVsAbstract

//Abstract classes can have everything that interfaces can. They can have fields and constructors.
//Therefore, we can properly hold states in abstract classes

//An abstract class permits you to make functionality that subclasses can implement or override, whereas an interface only permits you to declare functionality but not to implement it.

//Functions and properties with default bodies are final by default in abstract classes.
//We can also have a constructor and so pass values to this abstract class:

//Remember that classes marked with `abstract` do not need to the `open` keyword modifier to be able to extend them
abstract class Animal3(
    var name: String = "Default name",
    var type: String = "Default type"
) {
    val fullName: String
        get() = "$name of type $type"

    //When we have a primary constructor, we can have init blocks:
    init {
        println("Initializing $fullName")
    }

    //  Only abstract classes and interfaces can have abstract methods or variables. `abstract` keyword modifier is not optional like it is interfaces, in abstract classes `abstract` modifier must be explicitly declared.
    // abstract val abstractClassesCanHaveAbstractVariables: String = "a"
    // abstract fun abstractClassesCanHaveAbstractMethods()
}

class Fox3(name: String): Animal3(name, "Fox")
class Dog3(name: String): Animal3(name, "Dog")

fun main() {
    val fox = Fox3("Bobby")
    val dog = Dog3("Billy")
    println(fox.fullName) // Bobby of type Fox
    println(dog.fullName) // Billy of type Dog
}


//Remember, we can implement multiple interfaces, and can only extend a single abstract class.
//And we always have to call the constructor of the abstract class, while interfaces are implemented without constructors.
//Also Remember: We cannot instantiate interfaces and abstract classes (i.e. create objects of an abstract class), only through anonymous subclasses can we instantiate them "indirectly".
//The only and sole way to use a interface and abstract classes is via inheriting from them (be it with explicit subclasses or anonymous subclasses).
//In fact, the whole point of interfaces and abstract classes is to implement them in a subclass in the first place!
abstract class A
abstract class B
interface I1
interface I2
interface I3
class C: A(), I1, I2, I3 // if we try to extend B() it'll result in a compiler error because we can only extend one abstract class.

