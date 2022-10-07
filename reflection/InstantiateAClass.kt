package reflection

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor

fun main() {
    // using Java reflection to instantiate a class with a nullary constructor (i.e. a constructor that takes no arguments)
    val clazz = SomeRandomClass::class.java
    val instance = clazz.newInstance()
    instance.someFunction()

    // using Java reflection to instantiate a class with parameterized constructor
    val clazz2 = SomeOtherRandomClass::class.java
    val instance2 = clazz2.getConstructor(String::class.java, Int::class.java).newInstance("Mohammed", 25)
    instance2.someOtherFunction()

    // using Kotlin reflection to instantiate a class with a nullary constructor (i.e. a constructor that takes no arguments)
    val klass = SomeRandomClass::class
  //  val kInstance = klass.constructors.first().call() // alternatively
    val kInstance = klass.createInstance()
    kInstance.someFunction()

    // using Kotlin reflection to instantiate a class with parameterized constructor
    val klass2 = SomeOtherRandomClass::class
    val kInstance2 =  klass2.primaryConstructor?.call("Asmaa", 21)
    kInstance2?.someOtherFunction()
}

class SomeRandomClass() {
    fun someFunction() = println("I'm just some function of a no-args class!")
}

class SomeOtherRandomClass(val name: String, val age: Int) {
    fun someOtherFunction() = println("my name is $name and I'm $age years old")
}