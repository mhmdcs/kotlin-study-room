package functions

//read full article here, it's excellent, goes about reflection, function types, and how functions are objects in Kotlin
//due to Kotlin functions being first-class citizens.
//https://sorokod.github.io/post/2018-09-09-kotlin-functions-as-objects/

fun main(){
    fun test(x: Int, str: String) = x*2.4
    val testRef: Function2<Int, String, Double> = ::test

    fun x2(x: Int) = x * 2

    val x2ref: Function1<Int, Int> = ::x2

    println(::x2 is Function1<Int, Int>)   // prints true

    println(X2() is Function1<Int, Int>)   // prints true
    println(X2() is (Int) -> Int)          // prints true


    // to prove that function are objects, we will create an object of X2
    // and it'll be semantically the same as fun x2(x: Int) = x * 2

    println(x2(3)) // prints 6 using the fun x2(x: Int) = x * 2 function

    val objectX2 = X2()
    println(objectX2(3)) // prints 6 using the X2 class that implements Function1<Int, Int> and overrides invoke function

    // more operations on the X2 object
    println(listOf(1,2,3).map(objectX2)) // prints [2,4,6]
}

class X2: Function1<Int, Int> {
    override fun invoke(x: Int) = 2 * x
}
