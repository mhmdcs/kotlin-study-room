package functions

//Kotlin provides the ability to extend a class with new functionality without having to inherit from the class.
//This is done via special declarations called extensions.

//With extension functions, you can write new functions for a class from a third-party library that you can't modify.
//Such functions can be called in the usual way, as if they were methods of the original class.
//We will do an example of an extension function for the MutableList class/library, we'll be creating our own swap() function for it
//and we will be able to use it as if it were designed in the MutableList API.

//To declare an extension function, prefix its name with a receiver type, which refers to the type being extended.
//The following adds a swap function to MutableList<Int>:

//The `this` keyword inside an extension function corresponds to the receiver object (the one that is passed before the dot).
// Now, you can call such a function on any MutableList<Int>:
//fun MutableList<Int>.swap(index1: Int, index2: Int){
//    val temp = this[index1] // 'this' corresponds to the list
//    this[index1] = this[index2]
//    this[index2] = temp
//}

//This function makes sense for any MutableList<T>, and you can make it generic:

//You need to declare the generic type parameter before the function name to make it available in the receiver type expression.
fun <R> MutableList<R>.swap(index1: Int, index2: Int){
    val temp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = temp
}

//Kotlin supports extension properties much like it supports functions:
val <T> List<T>.lastIndex: Int
    get() = size - 1

fun main(){
    val list = mutableListOf(2, 4, 6, 9, 3)
    println(list)

    list.swap(0, 4) //calling extension function swap
    println(list)

    println(list.lastIndex) //calling extension property lastIndex
}