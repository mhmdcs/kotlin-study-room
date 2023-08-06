package misc

// The Comparable interface is a generic interface in Kotlin (and also in Java) used for ordering objects of the implementing class.
// Classes that implement the Comparable interface can be ordered in a "natural order," which allows objects of that class to be easily sorted, compared for equality, etc.

// The Comparable interface defines a single method, compareTo, that takes one argument of the same type as the implementing class and returns an Int.
// Here's what the method's signature looks like:  fun compareTo(other: T): Int
// If the object is less than the other object, compareTo should return a negative integer.
// If the object is equal to the other object, compareTo should return zero.
// If the object is greater than the other object, compareTo should return a positive integer.

// Here's an example of a class that implements Comparable:
data class Person(val name: String, val age: Int) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return this.age - other.age
        // The subtraction of this.age from other.age in the compareTo method is a common and concise way to implement a comparison based on integer values.
        // It serves the following purposes:
        // If this.age is less than other.age, then this.age - other.age will be negative, indicating that this is less than other.
        // If this.age is equal to other.age, then this.age - other.age will be zero, indicating that this and other are considered equal in terms of the comparison.
        // If this.age is greater than other.age, then this.age - other.age will be positive, indicating that this is greater than other.
    }
}

fun main() {
    // In this example, Person objects are compared based on their age property.
    // This makes it easy to sort a list of Person objects by age, for example:
    val people = listOf(
        Person("Alice", 30),
        Person("Bob", 25),
        Person("Charlie", 35)
    )
    val sortedPeople = people.sorted() // sorted by age
    println(sortedPeople)
}

// Implementing the Comparable interface allows classes to define a natural ordering, and it enables the use of functions like sorted, max, min, etc., that work with comparables.
// This interface helps to make the code more readable and provides a standard way to compare and order objects.

// The subtraction-based approach inside the implementation of compareTo() is a concise way to achieve the comparison, and it works well when you're comparing integer values like ages.
// However, be cautious with this method if the values can be large, as integer overflow might occur if the difference between the values is greater than what can be represented by an Int.
// In cases where overflow might be a concern, you might prefer to use comparison logic like:
//override fun compareTo(other: Person): Int {
//    return when {
//        this.age < other.age -> -1
//        this.age > other.age -> 1
//        else -> 0
//    }
//}
// This approach is more verbose but avoids the risk of overflow by directly returning -1, 0, or 1 based on the comparison of the ages.