package kotlin101

//more in this guide here https://www.javatpoint.com/kotlin-smart-cast

//To use a nullable type without the `?` safe-call operator, we have an option to use smart casts. Smart cast is a feature in which Kotlin compiler tracks conditions inside if expression.
//If compiler found that a nullable variable is not null, then the compiler will allow us to access the variable.

fun main() {
    var string: String? = "Hello!"

    //Without using smart-cast.
//    print(string.length) // Compile error

    //With smart-cast
    if(string != null) { //this is where smart cast occurs
        print(string.length) // It works now!
    }
}

//While using is or `!is` for checking the variable, the compiler tracks this information and internally
//casts the variable to the target type. This is done inside the scope only if `is` or `!is` return true.
//funmain() {
//    val obj: Any = "The variable obj of type Any will be automatically cast to a String inside the if-statement scope"
//    if(obj is String) { // `is` keyword checks that a value has a certain type, so this says "if obj's value has a type of String, then do the following". While the `!is` keyword checks that a value does NOT have a certain type.
//        // No Explicit Casting needed due to using `is`.
//        println("String length is ${obj.length}")
//    }
//}

//check the guide here https://www.javatpoint.com/kotlin-smart-cast for an example for `!is`
