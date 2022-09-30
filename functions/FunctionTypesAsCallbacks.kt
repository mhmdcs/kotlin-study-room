package functions

// function types, like functional interfaces, can be used to create callbacks/listeners

class Observed {
    private lateinit var listener: () -> Unit

    fun setListener(listener: () -> Unit) {
        this.listener = listener
    }

    fun doSomething() {
        println("I did something")
        listener.invoke() // also listener.() works since it implicitly calls the function type's invoke() function
    }
}

fun main() {
    // method 1: instantiating/implementing the function type callback with a lambda, which implicitly creates an object of anonymous class that implements () -> Unit function type and overrides its invoke() method
    val observed = Observed()
    observed.setListener {
        println("I observed doSomething() inside the Observed class and got called back")
    }
    observed.doSomething()

    // method 2
  //  Observer()
}

// method 2: instantiating the function type callback by explicitly constructing an object of a named class that implements the function type
class Observer: () -> Unit {
    init {
        val observed = Observed()
        observed.setListener(this)
        observed.doSomething()
    }

    override fun invoke() {
        println("I observed doSomething() inside the Observed class and got called back")
    }
}