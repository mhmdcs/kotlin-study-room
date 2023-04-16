package kotlin101


// TL;DR
// In Kotlin/Java, when you pass an object's reference to a function, you can only mutate the object's content, and you CANNOT reassign it.

// https://stackoverflow.com/a/59124310/9133569
// https://stackoverflow.com/a/70736703/9133569
fun main() {

    // if a reference to an object is passed to a method, and that object has public set variables and set methods, then its original state and behavior CAN be altered.
    val someObject = SomeObject()
    println(someObject.string)
    someMethod(someObject)
    println(someObject.string)

    // if a reference to an object is passed to a method, and that object doesn't have any public set variables and set methods (for example String, Int, etc these are all immutable objects), then its original state and behavior CANNOT be altered.
    val someString = "100"
    println(someString)
    someMethod(someString)
    println(someString)
}

fun someMethod(someString: String) {
  //  someString = "300" // compiler error, all function parameters are implicitly final aka val in Kotlin, this would compile in Java but it would do also nothing, since you cannot reassign objects that are passed to methods, be it primitives or complex objects, in the JVM.
}

fun someMethod(someObject: SomeObject) {
    someObject.string = "new something" // you can alter the original object's internal state/behavior if it has public set members
  // someObject = SomeObject() // compiler error, all function parameters are implicitly final aka val in Kotlin, this would compile in Java but it would do also nothing, since you cannot reassign objects that are passed to methods, be it primitives or complex objects, in the JVM.
}

class SomeObject {
     var string: String = "something"
}

/**
 * In other words, it's impossible to change the initial value of a primitive variable like Int or String by passing it as an argument
 * when calling a method. But this is possible with an object, but ONLY in the case it's a mutable object (i.e. it has public set methods).
 * For example, you can change the internals of your instance of your SomeObject class, or an instance of ByteArray, or AtomicBoolean,
 * or List, or Map class, etc with their public setter methods, but you can't change an Int or String, these classes/types are designed to be immutable, and thus have no public set methods.
 * **/