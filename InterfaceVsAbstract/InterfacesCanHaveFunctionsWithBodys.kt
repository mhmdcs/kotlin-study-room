package InterfaceVsAbstract

//full article here https://blog.kotlin-academy.com/abstract-class-vs-interface-in-kotlin-5ab8697c3a14

/*
Main difference between abstract classes and interfaces:
Interfaces cannot have fields
We can extend only one abstract class, while we can implement multiple interfaces
Abstract classes have constructors
* */

//Demonstrating that interfaces can have functions with concrete bodies
//Remember that classes marked with `interface` do not need to the `open` keyword modifier to be able to implement them
interface Animal1 {
    fun makeVoice() {
        print("<${this::class.simpleName} voice>") //accessing class's name using class reference reflection
    }
}

//Interface functions cannot be final, and they can always be overridden.
//This is a difference between an interface and an abstract class, where we can make function final in abstract, we can't in an interface.
//When we override interface functions, we can still use default body using super.
class Fox1: Animal1 {
    override fun makeVoice() {
        super.makeVoice() //if we omit this super call, <Fox voice> won't get printed since we didn't use the default body.
        print(" (I prefer to stay quiet)")
    }
}

fun main() {
    val fox = Fox1()
    fox.makeVoice() // <Fox voice> (I prefer to stay quiet)
}