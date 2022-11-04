package kotlin101

    fun higherOrder(lambda: () -> Unit) {
        lambda()
    }

    inline fun inlinedHigherOrder(lambda: () -> Unit) {
        lambda()
    }

fun main(args: Array<String>) {

    higherOrder { println("Hello, world") }
    inlinedHigherOrder { println("Hello, world") }

    // these two higher order function calls are essentially converted to:
  //  higherOrder { println("Hello, world") } // non-inlined higher order functions behave as you'd expect
  //  println("Hello, world") // <- inlining a higher order function essentially just copy-pastes its body! it gets directly inserted!

    higherOrder2 { println("Hello, world")
       // return // compiler error, non-local returns (global returns) are not allowed on crossinline lambdas
        return@higherOrder2
    }
}


// If you don't want all of the lambdas passed to an inline function to be inlined, mark some of your function parameters with the noinline modifier:
inline fun higherOrder(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    inlined()  // this will be inlined i.e. copy-pasted into the call-site
    notInlined() // this will not be inlined
}

// To indicate that the lambda parameter of the inlined higher-order function cannot use non-local returns (global returns), mark the lambda parameter with the crossinline modifier:
inline fun higherOrder2(crossinline body: () -> Unit) {
    val runnable = object: Runnable {
        override fun run() = body()
    }
    // ...
}