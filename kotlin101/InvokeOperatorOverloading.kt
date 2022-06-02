
//https://stackoverflow.com/a/45174200/9133569

//Kotlin allows you to provide custom implementations for the predefined set of operators on types.
//Such operators is the invoke function operator, we will provide a custom implementation of it on the String type.

//Overloading the invoke() operator.
//myClass(someString) is equivalent to myClass.invoke(someString)
class MyClass(val someString: String) {
    operator fun invoke() { //`invoke` function is a predefined operator in Kotlin to invoke classes. `operator` keyword only works on a limited set of predefined operators in the Kotlin language.
        println("$someString!")
    }
}


fun main() {
    val myClass = MyClass("Hello World")
    myClass()  // Prints "Hello World!"
    //myClass(someString) is equivalent to myClass.invoke(someString). invoke() is always implicitly called for classes.
}