package InterfaceVsAbstract

//Abstract classes can have everything that interfaces can, and additionally, they can have fields and constructors.
//Therefore, we can properly hold state in abstract classes

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
}

class Fox3(name: String): Animal3(name, "Fox")
class Dog3(name: String): Animal3(name, "Dog")

fun main() {
    val fox = Fox3("Sox")
    val dog = Dog3("Billy")
    println(fox.fullName) // Sox of type Fox
    println(dog.fullName) // Billy of type Dog
}


//Remember, we can implement multiple interfaces, and can only extend a single abstract class.
//And we always have to call the constructor of the superclass (abstract class in this case).
//Also Remember: We cannot instantiate abstract classes (i.e. create objects of an abstract class), while we can create objects of an interface.
//The only and sole way to use an abstract class is via inheriting from it (extending it).
abstract class A
abstract class B
interface I1
interface I2
interface I3
class C: A(), I1, I2, I3