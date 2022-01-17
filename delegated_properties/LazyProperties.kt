package delegated_properties

//lazy() is a function that takes a lambda and returns an instance of Lazy<T>, which can serve as a delegate for implementing a lazy property.
//The first call to get() executes the lambda passed to lazy() and remembers the result. Subsequent calls to get() simply return the remembered result.

val lazyValue: String by lazy {
    println("lazy() will remember that I've been already computed and won't compute me again!")
    "Hello"
}
//The evaluation of lazy properties is `synchronized`; meaning, the value is computed only in one thread, but all threads will see the same value.

fun main() {
    println(lazyValue)
    println(lazyValue)
}