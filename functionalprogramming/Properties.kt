package functionalprogramming

object PropertyImmutability {

    val value = "initial value" // val is thread-safe

    val changedValue get(): String { // val with a custom getter is NOT thread-safe
        return "new value"
    }
}

fun main() {

  //  PropertyImmutability.value = "newer value"
  //  PropertyImmutability.changedValue = "newer value"
    println(PropertyImmutability.value)
    println(PropertyImmutability.changedValue)
}