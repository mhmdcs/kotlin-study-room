package generics

// generics in classes

// Here, the content property must be of type String.
class MyClass1(val content: String)

// Here, the content property can be of any type, depending on how MyClass2 is instantiated.
class MyClass2<T>(val content: T)

// You can also add constraints to the generic type parameter if you want to restrict it to certain types.
// In this case, the content property must be a subtype of CharSequence.
class MyClass3<T : CharSequence>(val content: T)


// generics in functions

// Here, the list parameter must be of type List<String>, specifically a list of strings.
fun printLength1(list: List<String>) {
    println(list.size)
}

// Here, the list generic parameter can be a list of any type.
fun <T> printLength2(list: List<T>) {
    println(list.size)
}

// Here, the list constrained generic parameter can be a list of any subtype of CharSequence.
fun <T: CharSequence> printLength3(list: List<T>) {
    println(list.size)
}

// Constrained generic type parameters may seem counterintuitive if you consider the main goal of generics to be complete type flexibility.
// However, constrained generics offer several benefits that align with other important objectives of generics:

// Type Safety: By constraining a generic type parameter to a specific class or interface, you ensure that only compatible types can be used with the generic class or function. This helps to catch potential type errors at compile-time rather than runtime.

// Expressiveness: Constraints allow you to communicate more information about how a class or function should be used. If a function takes a generic parameter with a constraint that it must implement a particular interface, it's clear that the function expects objects that conform to that interface's contract.

// Access to Specific Methods or Properties: By constraining the generic type to a specific class or interface, you can call methods or access properties specific to that type within the generic code. Without the constraint, you wouldn't be able to call those methods without an explicit cast, losing some of the benefits of strong typing.
