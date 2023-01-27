package kotlin101

/**Structural Equality (==) & Referential Equality (===) differences in Kotlin

In short:
Use structural equality check (==) when you want to check instances(objects)

Use referential equality check (===) when you want to check the references of the instances(objects)

Structural equality (==) checks for equals() method.

Referential equality (===) checks whether the two references point to the same object. **/

fun main() {
    // Suppose we have a class as below:

    class Car(val color: String)

    // Note: This is NOT a data class. So, it will not implement the equals() method by default.

    // First, let's first compare structural equality:
    val car1 = Car("RED")
    val car2 = Car("BLUE")
    println(car1 == car2) // Outputs false

    val car3 = Car("RED")
    val car4 = Car("RED")
    println(car1 == car2) // Outputs false
    // It also outputs false even though the properties of both cars are the same, because we have not implemented the equals() method.
    // There are two ways to implement equals() method . Making the class a data class or adding our own equals() method implementation. Both will have the same effect. So, let's go with the data class.

    data class DataCar(val color: String)
    // When we make it a data class, it overrides equals () method internally.For regular (non - data) classes, the implementation of equals() is inherited from Any superclass, and the default implementation just makes the object equal to itself.

    // Here's how data class internally overrides and changed the implementation of equals() (and also toString() and hashCode())
    class DataCarInternally(val color: String) {

        override fun toString(): String = "Car(color=$color)"

        override fun equals(other: Any?): Boolean {
            if (other == null || other !is Car) {
                return false
            }
            return color == other.color
        }
    }

   // Now, let's first compare structural equality again after we have made Car a data class:
    val dataCar1 = DataCar("RED")
    val dataCar2 = DataCar("BLUE")
    println(dataCar1 == dataCar2) // Outputs: false

    val dataCar3 = DataCar("RED")
    val dataCar4 = DataCar("RED")
    println(car1 == car2) // Outputs: true
   //  It outputs true in the second example, as the color of both cars is the same, and we have made the class a data class that implements the equals() method.

  //  Now, in referential equality, data classes and equals() method have no effect on referential equality as referential equality only cares about whether or not two references point to the same object in the heap.
    println(dataCar3 === dataCar4) // Outputs: false
  //  It outputs as false since dataCar3 which references the first DataCar("RED") and dataCar4 which references the second DataCar("RED") are completely different objects, irregardless of having the same properties.

    val dataCar5 = dataCar4
    println(dataCar5 === dataCar4) // Outputs: true
   // It outputs true since dataCar4 reference points to DataCar("RED") object in the heap, and dataCar5 reference points to that same exact DataCar("RED") object in the heap as well.

    /** In short:
    //  Structural equality checks for the implementation of equals () method, if you go with the default implementation, it just checks if the object equals itself, but if you override it and give it a different implementation like data classes do, then it checks if the content of the properties of the objects are the same.
    //  Referential equality is like a more restricted structural equality that doesn't care about the properties being the same.**/

    // Another example:
    val x = listOf('b', 'c')
    val y = listOf('b', 'c')
    // both x and y are structurally equal(they have the same content, and the implementation of List's equals() checks for the equality of the object' s content), but both x and y references point to different instances of List on the heap.

    // As such, structural equality:
    println("${x == y}") // prints  true

    // While referential equality
    println("${x === y}") // prints false


    fun argsArePassedByReferenceInKotlin(z: List<Char>) = z === x
    // arguments are passed as references in Kotlin, when we pass a reference to a function, inside that function, the passed-in reference will point to the same object in the heap
    // meaning that, if we pass our x reference to argsArePassedByReferenceInKotlin() as the z parameter, both x and z references will point to the same object in the heap, and the referential check z === x will be true
    // this also means that if any changes mutated z, then it'll affect x as well :)
    println(argsArePassedByReferenceInKotlin(x)) // prints true
}