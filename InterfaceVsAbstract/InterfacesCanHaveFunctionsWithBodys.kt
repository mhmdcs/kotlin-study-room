package InterfaceVsAbstract

//full article here https://blog.kotlin-academy.com/abstract-class-vs-interface-in-kotlin-5ab8697c3a14

/*
Main difference between abstract classes and interfaces:
Interfaces cannot have fields (in other words, they can't store states)
We can extend only one abstract class, while we can implement multiple interfaces
Abstract classes have constructors, interfaces don't.
* */

//Demonstrating that interfaces can have functions with concrete bodies
//Remember that classes marked with `interface` do not need to the `open` keyword modifier to be able to implement them
interface Animal1 {
    fun makeVoice() { // not an abstract method because it has a default body {}
        print("<${this::class.simpleName} voice>") //accessing class's name using class reference reflection
    }

    //An interface can never have a state, so it cannot use mutable instance variables. An interface can only use final variables.

    //  Only abstract classes and interfaces can have abstract methods or variables. `abstract` keyword modifier is optional in interfaces because it is implicitly declared.
    // abstract val interfacesCanHaveAbstractVariables: String
    // abstract fun interfacesCanHaveAbstractMethods()
}

//Interface functions cannot be final, and they can always be overridden.
//This is a difference between an interface and an abstract class, where we can make function final in abstract, we can't in an interface.
//When we override interface functions, we can still use default body using super.
class Fox1: Animal1 {
    // because makeVoice() has a default body {}, it is not implicitly abstract anymore, and overriding it is optional.
    // if we remove the override fun makeVoice() below, our code will still compile because makeVoice() has a default body
    // if we removed the default body {} from the Fox1 interface, then overriding makeVoice() would become mandatory since it's abstract and needs an implementation.
    override fun makeVoice() {
        super.makeVoice() //if we omit this super call, <Fox voice> won't get printed since we didn't use the default body.
        print(" (I prefer to stay quiet)")
    }

    //A normal class(non-abstract class) cannot have abstract methods or variables. Only abstract classes and interfaces can have abstract methods or variables. If we uncomment the following two lines, it'll result in a compiler error.
   // abstract val normalClassesCantHaveAbstractVariables: String
   // abstract fun normalClassesCantHaveAbstractMethods()
}

fun main() {
    val fox = Fox1()
    fox.makeVoice() // <Fox voice> (I prefer to stay quiet)
}