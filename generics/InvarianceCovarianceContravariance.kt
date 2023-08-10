package generics
/**   https://docs.scala-lang.org/tour/variances.html  */

open class Animal // supertype

data class Cat(var name: String = "Kitty"): Animal() // subtype

class Cage<Type> // invariance

class Box<out Type>(val content: Type) // covariance

abstract class Serializer<in Type> { // contravariance
    abstract fun serialize(type: Type)
}

fun main() {
    val animalBox: Box<Animal>
    val catBox: Box<Cat> = Box<Cat>(Cat())
    animalBox = catBox // an object of a subtype can be assigned to a variable of a supertype thanks to covariance - the subtyping is preserved - out, producer, reading from generic parameters.
    println(animalBox.content)

    val catSerializer: Serializer<Cat>
    val animalSerializer: Serializer<Animal> = object: Serializer<Animal>() {
        override fun serialize(type: Animal) = println("{ \"name\": \"$type\" }")
    }
    catSerializer = animalSerializer // an object of a supertype can be assigned to a variable of a subtype thanks to contravariance - the subtyping is inversed - in, consumer, writing to generic parameters.
    catSerializer.serialize(Cat("Kat"))
}


/**
 * Variance lets you control how type parameters behave with regards to subtyping.
 * Kotlin supports declaration-site variance of type parameters of generic classes using `out` and `in` modifiers, to allow them to be covariant, contravariant, or invariant if no modifier are used.
 * The use of variance in the type system allows us to make intuitive connections between complex types.
 *
 * class Box<out A> // A covariant class
 * class Box<in A> // A contravariant class
 * class Box<A>  // An invariant class
 *
 * Invariance
 * By default, type parameters in Kotlin are invariant: subtyping relationships between the type parameters aren’t reflected in the parameterized type.
 * To explore why this works the way it does, we look at a simple parameterized type, the mutable box.
 * Read more from Scala's docs, it's the best explanation for invariance, covariance, contravariance:
 * https://docs.scala-lang.org/tour/variances.html
 * */




/**
 *
Covariance is a generic type where the subtyping is preserved.
Contravariance is a generic type where the subtyping is inversed.

Making a type parameter a covariant type makes it possible to be able to read values even when the supplied type doesn't match the one in the type parameter in the class variable, or function definition.

Making a type parameter a contravariant type makes it possible to be able to pass values as function arguments even when the type arguments don't match the ones in the type parameter in the function definition.

Consider an Animal superclass and a Cat and a Dog subclasses that extend Animal

The relationship from Cat to Animal, and Dog to Animal, is covariant.
Covariance only goes one way. We can say that a Cat is a subtype of an Animal, but we can't say that an Animal is necessarily a Cat (it could be a Dog). The subtyping for the Animal class is always preserved in covariance.
Now consider a Box class that holds these types
If Box type's interface was declared as Box <out T>, then you can assign an instance of Box<Cat> to a variable of type Box<Animal>.

The relationship from Animal to Cat, and Animal to Dog, is contravariant.
Contravariance on the other hand is a reflection of covariant, it's kind of a mirrored version of covariant. The subtyping for the Animal class is always reversed in contravariance.
If Box type's interface was declared as Box <in T>, then you can assign an instance of Box<Animal> to a variable of type Box<Cat>.


Covariance
Box<out Producer>
To declare a class to be covariant on a type parameter we use the keyword `out` to produce the element type in Kotlin, or `? extends` wildcard in Java.

Contravariance
Box<in Consumer>
To declare a class to be contravariant on a type parameter we use the keyword `in` to consume the element type in Kotlin, or `? super` wildcard in Java.


In other words, covariance is the quality of being different by being more specific (Cat is covariant to Animal), while contravariance is the quality of being different by being more general (Animal is contravariant to Cat).

 * **/


/**
Invariance:
- Invariance is the default behavior.
- You can neither assign a subtype nor a supertype of T to a Box<T>.
- Example: If you have class Box<T>, Box<Any> is not a supertype of Box<String>, and you cannot assign Box<String> to a variable of type Box<Any>.

Covariance:
- Covariance is marked with the `out` keyword, and it allows reading values of type T or its subtypes.
- You can assign a subtype of T to a Box<out T>, but you cannot modify the contents (you can't write or set T).
- It's safe to read from a covariant type because you know that you'll always get at least a T (or a subtype).
- Example: If you have class Box<out T>, Box<Any> is a supertype of Box<String>, and you can assign Box<String> to a variable of type Box<Any>.

Contravariance:
- Contravariance is marked with the `in` keyword, and it allows writing values of type T or its supertypes.
- You can assign a supertype of T to a Box<in T>, but you cannot read the contents (you can't read or get T).
- It's safe to write to a contravariant type because you know that you're always writing at least a T (or a supertype).
- Example: With abstract class Serializer<in T>, Serializer<Animal> is a subtype of Serializer<Cat>, so you can assign Serializer<Animal> to a variable of type Serializer<Cat>.


Covariant (out): You can read T or its subtypes. Subtyping is preserved.
Contravariant (in): You can write T or its supertypes. Subtyping is reversed.
Invariant: You can read and write T, but no subtyping or supertyping is allowed.

 * **/

/**
Read-only data types (sources) can be covariant (out); write-only data types (sinks) can be contravariant (in).
Mutable data types which act as both sources and sinks (i.e. can be both read from and written to) should be invariant.

Consider the generic Box we defined earlier along with the Animal superclass and Cat subclass.
For the type Animal, we can make the type Box<Animal>, which is a "box of animals", or Box<Cat>,  which is a "box of Cats".
An invariant Box is defined as Box<T>, a covariant box is defined as Box <out T>, and a contravariant Box is defined as Box<in T>.

We have the option to treat the type of Box as either:

- Covariant Box<out T>: a Box<Cat> is a Box<Animal> (subtyping is preserved).

- Contravariant Box<in T>: a Box<Animal> is a Box<Cat> (subtyping is reversed).

- Invariant Box<T>: a Box<Animal> is not a Box<Cat>, and a Box<Cat> is not a Box<Animal>; neither Box<Cat> nor Box<Animal> is a subtype of the other, a Box<Animal> can only be a Box<Animal>.

If we wish to avoid type errors, then only the third choice (invariance) is safe due to its strictness and constrainess on the type.

Clearly, covariance isn't safe, a Box<Cat> cannot be treated as an Box<Animal>. It should always be possible to put a Box<Dog> into an Box<Animal>. With covariant boxes, this cannot be guaranteed to be safe, since the backing store might actually be an box of cats. The covariant rule is not safe, the box constructor should be invariant.
Note that this is only an issue for mutable boxes; the covariant rule is only safe for immutable (read-only) boxes.

Conversely, contravariance isn't safe either; not every Box<Animal> can be treated as if it were a Box<Cat>, since a client reading from the box will expect a Cat, but an Box<Animal> may contain e.g. a Dog. So the contravariant rule is not safe for reading. The contravariant rule is only safe for write-only boxes.
 * **/