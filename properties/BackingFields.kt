package properties

//In Kotlin, a field is only used as a part of a property to hold its value in memory. Fields cannot be declared directly.
//However, when a property needs a backing field, Kotlin provides it automatically. This backing field can be referenced in the accessors using the `field` identifier:

var counter = 0 // the initializer assigns the backing field directly
    set(value) {
        if (value >= 0)
            field = value //backing field referenced using the `field` identifier. The `field` identifier can only be used in the accessors of the property (both setters and getters).
        // counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
    }
//By convention, the name of the setter parameter is value, but you can choose a different name if you prefer.

fun main() {
    counter = -3 //used custom setter accessor which prevents setting values that are less than 0
    println(counter)
    counter = 5 //used custom setter accessor which prevents setting values that are less than 0
    println(counter)
}

//A backing field will be generated for a property if it uses the default implementation of at least one of the accessors,
//or if a custom accessor references it through the `field` identifier, or if the initializer assigns it directly
//For example, there would be no backing field in the following case:
/**
val isEmpty: Boolean
get() = this.size == 0
 * */
//no backing field generated since it neither uses the default implementation of getter method, nor did it use the `field` identifier, nor did it have an initializer to assign it directly.