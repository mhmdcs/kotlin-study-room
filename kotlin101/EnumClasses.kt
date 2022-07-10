package kotlin101


//https://kotlinlang.org/docs/enum-classes.html

//The most basic use case for enum classes is the implementation of type-safe enums
//Each enum constant is an object. Enum constants are separated by commas. You can add an optional comma after the last value, or a semicolon, as a treat!
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


//Since each enum is an instance of the enum class, it can be initialized as:

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//Every enum constant has properties for obtaining its name and position in the enum class declaration
// color.name and color.ordinal respectively for the enum class above.