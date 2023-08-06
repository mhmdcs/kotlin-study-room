package properties

import kotlin.math.sqrt

//backing field = field
//same thing, but we'll call it field for short here

class Square {

    //field is how the property's value is to held in memory, it refers to the sideLength's value 4.0, and color's value "Blue" in this case

                           //field
    var sideLength: Double = 4.0 // the `=` initializer assigns the field directly
        get() = field //Kotlin auto-generates this getter implicitly for us, if we explicitly call it like this, the compiler yells at us that it's redundant.
        set(value) { field = value } //Kotlin auto-generates this setter implicitly for us, if we explicitly call it like this, the compiler yells at us that it's redundant.

                       //field
    val color: String = "Blue" // the `=` initializer assigns the backing field directly
        get() = "$field-ish" //the compiler doesn't yell at us anymore because we've modified color's getter, we created a custom getter accessor.

    var area: Double //field will not be generated because 1) we have not initialized this property, 2) we did not declare `field` in neither custom the accessors (declaring it in at least one of them would be enough)
        get() = sideLength*sideLength
        set(value){
            sideLength = sqrt(value)
        }
}

fun main(){

    val square = Square()

    val side = square.sideLength // indirectly accessing the field via Kotlin's auto-generated getter accessor
    square.sideLength = 5.0 // indirectly accessing the field via Kotlin's auto-generated setter accessor

    val person = Person()
    person.name
    person.name = "Soso"
    person.name
}

class Person {
    // this basically how all properties in Kotlin are handled under the hood, they all have implicitly compiled getter and setter access methods
    // represented by get() and set(value), where get() returns the field, and set(value) sets the value to the field.
    var name: String = "Alice"
        get() {
            println("Getting name: $field")
            return field
        }
        set(value) {
            println("Setting name: $value")
            field = value // 'field' refers to the backing field here
        }
}