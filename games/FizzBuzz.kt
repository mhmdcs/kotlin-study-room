package games

fun main() {
    val numbers = listOf(3, 4, 25, 9, 6, 23, 25, 16, 15, 20, 29, 45, 30)
//    for (number in numbers) {
//        if (number % 5 == 0 && number % 3 == 0)
//            println("FizzBuzz")
//        else if (number % 3 == 0)
//            println("Fizz")
//        else if (number % 5 == 0)
//            println("Buzz")
//        else println(number)
//    }

//    for (number in numbers) {
//        when {
//            number % 15 == 0 -> println("FizzBuzz")
//            number % 3 == 0 -> println("Fizz")
//            number % 5 == 0 -> println("Buzz")
//            else -> println(number)
//        }
//    }

//    numbers.forEach { number ->
//        when {
//            number % 15 == 0 -> println("FizzBuzz")
//            number % 3 == 0 -> println("Fizz")
//            number % 5 == 0 -> println("Buzz")
//            else -> println(number)
//        }
//    }

    var turnedOn = true

    println("FizzBuzz Game.\nEnter multiplier of 3 for Fizz, multiplier of 5 for Buzz, multiplier of both 3 and 5 for FizzBuzz.\nEnter -1 to quit")
    while(turnedOn) {
        val input = readln()
        val number = input.toInt()
        when {
            number % 15 == 0 -> println("FizzBuzz")
            number % 3 == 0 -> println("Fizz")
            number % 5 == 0 -> println("Buzz")
            input == "-1" -> {
                println("Exited Game.")
                turnedOn = false
            }
            else -> println(number)
        }
    }
}