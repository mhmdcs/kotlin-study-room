package random_misc

fun main() {

    val myValue: String? = "some text"

    // let() causes unexpected side-effects if it returns null at the end of the block, because run() will be executed too, even if myValue is NOT null.
    myValue?.let {
        println("myValue is NOT null")
        someUnrelatedNullableComputation()
    } ?: run {
        println("myValue IS null") // this block of code wil be executed because someUnrelatedNullableComputation() returned null!
    }

    if(myValue != null) {
        println("myValue is NOT null")
        someUnrelatedNullableComputation()
    } else {
        println("myValue IS null")
    }

    // instead, use also() in place of let() to replicate the same behavior of the traditional if( a!= null){} else{} null-check
    myValue?.also {
        println("myValue is NOT null")
        someUnrelatedNullableComputation()
    } ?: run {
        println("myValue IS null")
    }
}

fun someUnrelatedNullableComputation(): Int? {
    return null
}