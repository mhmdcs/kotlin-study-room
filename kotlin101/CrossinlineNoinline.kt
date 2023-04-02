package kotlin101

fun main() {
    compareInts(2, 5) {
        println(it)
    }
}

// when you want to inline a function that has a lambda as a parameter, but the lambda
// gets invoked inside ANOTHER lambda, or inside a single abstract method interface,
// then crossinline the parameter lambda so that when the object that implements the functional interface
// gets created, the lambda's body (in our case the println(it) statement) gets inlined inside of it
inline fun compareInts(a: Int, b: Int, crossinline block: (String) -> Unit) {
    val task = Runnable {
        if (a > b)
            block("a  > b")
        if (a < b)
            block("a < b")
        else
            block("a = b")
    }
    task.run()
}

// same as above
//inline fun compareInts(a: Int, b: Int, crossinline block: (String) -> Unit) {
//    val task: () -> Unit = {
//        if (a > b)
//            block("a  > b")
//        if (a < b)
//            block("a < b")
//        else
//            block("a = b")
//    }
//    task.invoke()
//}