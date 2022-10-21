package functionalprogramming

// https://youtu.be/eNe5Nokrjdg

fun main() {
    higherOrder(::sum)
    higherOrder { param1, param2 ->
        param1 + param2
    }

    val numbers = 1..20
    numbers.filter { it % 5 == 0 }
            .map { it * 2 }
            .forEach { println(it) }

     "HELLO".toCamelCase()

    "Me".isSameAs("Me")
    "Mine" isSameAs "Mine"

    container()

}

fun container() {
    val numbers = 1..100
    numbers.forEach {
        if(it % 5 == 0) {
            return // global return that will exist to outside of the container() function, this global return is possible inside forEach()'s body because forEach() is an inline function, which means it won't exist at runtime time and whatever code exists in it will be copy-pasted to here
          //  return@forEach // local labeled return that will exist to just the outside of the forEach() function
        }
    }
    println("Hello")
}

fun String.toCamelCase() {
  //  TODO()
}

infix fun String.isSameAs(value: String) = this == value

fun higherOrder(function: (Int, Int) -> Int) {
    println(function(1, 1))
}

fun sum(param1: Int, param2: Int) = param1 + param2