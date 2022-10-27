package designprinciples

// Liskov Substitution Principle

// The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects
// of its subclasses without breaking the application without without any unexpected behaviour.
// In other words, what we want is to have the objects of our subclasses behaving the same way as the objects of our superclass.

fun main() {
    val subclass = Subclass()
    method(superclass = subclass)
}


open class Superclass
class Subclass: Superclass()

fun method(superclass: Superclass) {
    println("I was passed a ${superclass.javaClass.simpleName} when I actually expect a Superclass")
}