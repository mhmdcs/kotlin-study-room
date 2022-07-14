package kotlin101


//https://kotlinlang.org/docs/enum-classes.html

//The most basic use case for enum classes is the implementation of type-safe enums. A fixed, enumerate list of predefined values that we know.
//Each enum constant is an object. Enum constants are separated by commas. You can add an optional comma after the last value, or a semicolon, as a treat!
//Each enum constant is a single instance, meaning that they are *final* and *static* implicitly by default, which means that they are unchangeable, and only one instance of them exists.
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


//Since each enum is an instance of the enum class, it can be initialized as:

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun main(){
    val blueColor = Color.BLUE
    val redColor = Color.RED.ordinal
    val greenColor = Color.GREEN.name
    println("Color.BLUE = $blueColor | Color.RED.ordinal = $redColor | Color.GREEN.name = $greenColor")
}

//Every enum constant has properties for obtaining its name and position in the enum class declaration
// color.name and color.ordinal respectively for the enum class above.