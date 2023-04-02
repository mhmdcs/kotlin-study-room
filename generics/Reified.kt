package generics

//fun <T> printType() {
//    print(T:class.java) // compiler error, you cannot access a generic parameterized type directly at compile time due to type erasure at runtime.
//}

// There's a workaround. You can access the deleted generic type by passing the class of the generic type as a parameter of the function,
// then use reflection to access the type information.
fun <T> printTypeWithoutReified(classType: Class<T>) {
    println(classType.simpleName)
}

// The above code isn't too bad, but Kotlin addresses the problem elegantly with the unique keyword `reified`.
inline fun <reified T> printType() {
    println(T::class.java.simpleName)
}

fun main() {

    // to access the generic parameterized type without reified, you would have to pass in the type's Class instance
    printTypeWithoutReified<String>(String::class.java)

    // when using inline reified, we can access the generic parameterized type directly inside the method
    printType<String>()

    // Normally, a function cannot be overloaded if it has the same input and just different return types.
    // but thanks to reified, it allows us to overload functions that just return different generic types.
    val intCall: Int = calculate(123643f)
    val floatCall: Float = calculate(123643f)
    println("intCall is $intCall and floatCall is $floatCall")
}

// Another neat feature is that reified also allow us to overload a function by different return generic types.
// For example, the following function can return an int or float.
inline fun <reified T> calculate(value: Float): T {
    return when (T::class) {
        Float::class -> value as T
        Int::class -> value.toInt() as T
        else -> throw IllegalStateException(
            "Only works with Float and Int")
    }
}

/**
Generics provide type safety and help you avoid explicit typecasts.
Generics extend the type system to allow a type or method to operate on objects of various types while providing compile time type safety.
On the other hand, generics can be limiting when you need to access type info in a generic function and the compiler tells you the info doesn't exist.
Kotlin once again has a more elegant way to deal with this with the `reified` keyword.
Here's how to access the type info of generic parameters by using `reified`
The missing type info is a result of how generics are implemented in the JVM. It's because of "type erasure", which we will discuss later.

With reified, you can access the type info from within a generic function.

If you are familiar with how generics work, you might be asking how this is even possible. Let's see how. Let's talk about generics first.
Before generics were retrofitted in Java with version 5, type info didn't exist in collections.
This means there is no indication if an array list is an ArrayList of string, integer, or any other type.

List list = new ArrayList();
list.add("First String");
list.add(6); // No error!!! Super unsafe.

Without generics, each time you want to access an object in a collection, you need to perform an explicit cast.
Plus, there is no guard against invalid casts, which will result in runtime exceptions.
We need to explicitly cast to access our String ArrayList indices, but will throw exception ClassCastException at runtime because index 1 contains an Integer.

String str = (String)list.get(1); // will throw exception at runtime

To address this, generics were added in Java 5.
With generics, you can define a specific type for a collection, and the compiler will warn you if you try to add any other type.
Also, you don't need to perform explicit casts, which might result in runtime exceptions.

List<String> list = new ArrayList<>();
list.add("First String");
list.add(6); // Compile error saves us from crashing

String str = list.get(0); // notice no cast is needed anymore

Generics are implemented with a trick called type erasure.
Since there was no type info before Java 5, the Java compiler first replaces all type info with a base Object type, and adds the necessary type cast when it needs to access those objects.
Type erasure allows both compile-time type-safety (by providing type information to the compiler) as well as backwards comparability by keeping the byte code the same as on previous Java versions.
Meanwhile, type erasure can be limiting when you need the type info in a generic function.

Now, let's see how `reified` manages to access type info at runtime that should have been erased at compile time.
To tackle this problem, reified makes use of inline functions.
To recap on what inline functions are, if a function is marked as inline, the Kotlin compiler will copy the function body to the call-site; it will copy the body to every place where the function is called.
One advantage of this is that the compiler is also free to modify the function body as it is being copied over.

To use reified parameter types, you first need to mark the function as inline, and then add the reified keyword to the generic parameter.

inline fun <reified T> printType() {
print(T:class.java)
}

fun main() {
printType<String>()
}

What happens under the hood in the decompiled Java code? When there's a call to the inline function with a reified type, the compiler will copy the function body and then it will replace the generic type with the actual declared type at the call site.
As a result, you don't need to pass the class to access the type info :)

Reified can be used only with inline functions, so the same rules that apply to inline functions also apply to reified.
Also, keep in mind when writing libraries that reified functions cannot be accessed from Java. Java doesn't support in-lining, and without in-lining, generic parameters cannot escape being erased by the compiler.

Another neat feature is that reified also allows overloaded functions to return generic types.

For example, the following function can return an int or float.

inline fun <reified T> calculate(value: Float): T {
return when (T::class) {
Float::class -> value as T
Int::class -> value.toInt() as T
else -> throw IllegalStateException(
"Only works with Float and Int")
}
}

val intCall: Int = calculate(123643)
val floatCall: Float = calculate(123643)

Normally, a function cannot be overloaded if it has the same input and just different return types.
With the inline functions, once again, the compiler can replace the generic return type with the expected type while copying the function body.

Reified allows you to do things with generics which were previously not  possible due to lack of type info at runtime.
Use reified when you need the class type info in your inline function, or when you want to overload generic return types.

Reified doesn't introduce any performance penalties, but inlining a large function can.
Since a function needs to be inlined in order to use reified types, don't forget to keep your reified functions short to avoid performance penalties, and follow the best practices for inline functions.
 * **/