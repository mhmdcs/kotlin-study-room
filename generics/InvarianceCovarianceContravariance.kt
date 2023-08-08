package generics
/**   https://docs.scala-lang.org/tour/variances.html  */

open class Animal // supertype

data class Cat(var name: String = "Kitty"): Animal() // subtype

class Cage<Type> // invariance

class Box<out Type>(val content: Type) // covariance

abstract class Serializer<in Type> { // contravariance
    abstract fun serialize(t: Type)
}

fun main() {
    val animalBox: Box<Animal>
    val catBox: Box<Cat> = Box<Cat>(Cat())
    animalBox = catBox // a subtype object can be assigned to a supertype variable thanks to covariance
    println(animalBox.content)

    val catSerializer: Serializer<Cat>
    val animalSerializer: Serializer<Animal> = object: Serializer<Animal>() {
        override fun serialize(t: Animal) = println("{ \"name\": \"$t\" }")
    }
    catSerializer = animalSerializer // a supertype object can be assigned to a subtype variable thanks to contravariance
    catSerializer.serialize(Cat("Kat"))
}


/**
 * Variance lets you control how type parameters behave with regards to subtyping.
 * Kotlin supports declaration-site variance of type parameters of generic classes using `out` and `in` modifiers, to allow them to be covariant, contravariant, or invariant if no modifier are used.
 * The use of variance in the type system allows us to make intuitive connections between complex types.
 *
 * class Foo<out A> // A covariant class
 * class Bar<in A> // A contravariant class
 * class Baz<A>  // An invariant class
 *
 * Invariance
 * By default, type parameters in Kotlin are invariant: subtyping relationships between the type parameters aren’t reflected in the parameterized type.
 * To explore why this works the way it does, we look at a simple parameterized type, the mutable box.
 * Read more from Scala's docs, it's the best explanation for invariance, covariance, contravariance:
 * https://docs.scala-lang.org/tour/variances.html
 * */




/**
 *
A covariance is a generic class where the subtyping is preserved.
A contravariance is a generic class where the subtyping is inversed.

Making a type parameter a covariant type makes it possible to be able to read values even when the supplied type doesn't match the one in the type parameter in the variable or function definition.

Making a type parameter a contravariant type makes it possible to be able to pass values as function arguments even when the type arguments don't match the ones in the type parameter in the function definition.

Consider an Animal superclass and a Cat and a Dog subclasses that extend Animal

The relationship from Cat to Animal, and Dog to Animal, is covariant.
Covariance only goes one way. We can say that a Cat is a subtype of an Animal, but we can't say that an Animal is necessarily a Cat (it could be a Dog). The subtyping for the Animal class is always preserved.
Now consider a Box class that holds these types
If Box type's interface was declared as `Box <out T>`, then you can assign an instance of Box<Cat> to a variable of type Box<Animal>.

The relationship from Animal to Cat, and Animal to Dog, is contravariant.
Contravariance on the other hand is a reflection of covariant, it's kind of a mirrored version of covariants.
If Box type's interface was declared as `Box <in T>`, then you can assign an instance of Box<Animal> to a variable of type Box<Cat>.


Covariance
Box<out Producer>
To declare a class to be covariant on a type parameter we use the keyword `out` to produce the element type in Kotlin, or ? extends wildcard in Java.

Contravariance
Box<in Consumer>
To declare a class to be contravariant on a type parameter we use the keyword `in` to consume the element type in Kotlin, or ? super wildcard in Java.


In other words, covariance is the quality of being different by being more specific (Cat is covariant to Animal), while contravariance is the quality of being different by being more general (Animal is contravariant to Cat).
 *
 * **/