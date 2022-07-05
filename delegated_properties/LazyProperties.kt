package delegated_properties

//lazy() is a function that takes a lambda and returns an instance of Lazy<T>, which can serve as a delegate for implementing a lazy property.
//The first call to myLazyValue's get() accessor executes the lambda passed to lazy() and remembers the result. Subsequent calls to get() simply return the remembered result.

val myLazyValue: String by lazy {
    println("lazy() will remember that I've been already computed and won't compute me again!")

    "Hello"
}

val myLazyIntValue: Int by lazy {
    var number = 2
    number+=3
    println("lazy() will remember that I've been already computed and won't compute me again!")

    number
}
//The evaluation of lazy properties is `synchronized`; meaning, the value is computed only in one thread, but all threads will see the same value.

fun main() {
    println(myLazyIntValue) // will compute 2+3 then print 5
    println(myLazyIntValue) // will print 5 again and won't compute by adding another 3, because the lazy initialization remembers the result
}