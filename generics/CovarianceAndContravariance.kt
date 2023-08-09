package generics

// Generics in Kotlin: Covariance and Contravariance

fun main() {
    // consider this code:
    val elements1: MutableList<Any> // MutableList<E> (invariance! we're limited to ONLY List objects of specifically and only type Any!)
    val strings1: MutableList<String> = mutableListOf("Some", "Random", "Stuff")
//    elements1 = strings1 // compile error

// but this code:
    val elements2: List<Any> // List<out E> (covariance! we can pass List objects with a subtype of Any, like MutableList<String> or List<String>!)
    val strings2: MutableList<String> = mutableListOf("Some", "Random", "Stuff")
    elements2 = strings2 // successfully compiles
// This is possible because List interface is a covariant generic class defined as List<out T> :)
// Thus now you can assign an instance of MutableList<String> to a variable of type List<Any>

}

/**Generics: Covariants (out) and Contravariants (in) in Type Parameters <>

in is "for input" - you wanna put(write) something into it (so it's a "consumer")

out is "for output" - you wanna take(read) something out of it (so it's a "producer")

If you're from Java,

<in T> is for input, so it's like <? super T> (consumer)

<out T> is for output, so it's like <? extends T> (producer)

---

`out` keyword in a generic type parameter denotes Co-variance.
`in` keyword in a generic type parameter denotes Contra-variance.

Remember that "in"put and "contra"-variance both have an n :)

------

A covariance is a generic class where the subtyping is preserved.

Making a type parameter a covariant class makes it possible to be able to pass values as function arguments even when the type arguments don't match the ones in the type parameter in the function definition.

Consider a Mammal superclass and a Sloth and a Panda subclasses that extend Mammal

The relationship from Sloth to Mammal is covariant, and the relationship from Panda to Mammal is covariant.
Covariance only goes one way. We can say that a Panda is a subtype of a Mammal, but we can't say that a Mammal is necessarily a Panda (it could be a Sloth). The subtyping for the class is always preserved.

The relationship from Mammal to Sloth is contravariant, and the relationship from Mammal to Panda is contravariant.
Contravariance on the other hand is a reflection of covariant, it's kind of a mirrored version of covariants.

Covariance
Producer<out Mammal>
To declare a class to be covariant on a type parameter we use the keyword `out` to produce the element type.

Contravariance
Consumer<in Mammal>
To declare a class to be contravariant on a type parameter we use the keyword `in` to consume the element type.


---------

When referring to the generic type system, Covariance and Contravariance, have the following definitions. The examples assume a base (super)class named Base and a derived (sub)class named Derived.

Covariance:
Enables you to use a more derived (i.e. more specific) type than originally specified.
You can assign an instance of List<Derived> to a variable of type List<Base>.

Contravariance:
Enables you to use a more generic (less derived i.e. less specific) type than originally specified.
You can assign an instance of List<Base> to a variable of type List<Derived>.

------------------

In other words, covariance is the quality of being different by being more specific (Cat is covariant to Animal) while contravariance is the quality of being different by being more general (Animal is contravariant to Cat).e
 **/