package functions.FunctionTypesAsCallbacks

// same exact thing as the FunctionTypesAsCallbacks1 file, but using the traditional functional interface as a callback instead of a function type

class Observed2 {
    private lateinit var listener: Listener

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun doSomething() {
        println("I did something")
        listener.onDoSomething()
    }
}

fun interface Listener {
    fun onDoSomething()
}

fun main() {
    val observed = Observed2()
    observed.setListener {
        println("I observed doSomething() inside the Observed class and got called back")
    }
    observed.doSomething()
}