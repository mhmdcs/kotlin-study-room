package misc

// https://miro.medium.com/max/1400/1*i-Uo4RQDd6tNi2Mj8WSUoA.jpeg

fun main() {

    val myValue: String? = "some string value"

    // 1 - using ?.let {}?: run {}
    // let() might cause an unexpected subtle bug if it returns null at the end of the code block, causing run() to execute too, even if myValue is NOT null.
    myValue?.let {
        println("1 - myValue is NOT null")
        someNullableComputation()
    } ?: run {
        println("1 - myValue IS null") // this block of code will be executed because someNullableComputation() returned null!
    }

    // 2 - if-else null check
    if(myValue != null) {
        println("2 - myValue is NOT null")
        someNullableComputation()
    } else {
        println("2 - myValue IS null")
    }

    // 3 - using ?.also {}?: run {}
    // instead, use also() in place of let() to replicate the same behavior of the traditional if(myValue != null){} else{} null-check
    myValue?.also {
        println("3 - myValue is NOT null")
        someNullableComputation()
    } ?: run {
        println("3 - myValue IS null")
    }
}

fun someNullableComputation(): Int? {
    return null
}