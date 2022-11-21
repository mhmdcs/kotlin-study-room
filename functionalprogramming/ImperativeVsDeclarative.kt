package functionalprogramming

// https://www.youtube.com/watch?v=eNe5Nokrjdg&t=564s&ab_channel=GOTOConferences

fun main() {
    val elements = listOf("One", "Two", "Three")
    val myHashMap = itDoesSomething(elements)
    val myPairs = itDoesSomethingAlso(elements)
    println(myHashMap)
    println(myPairs)
}

// imperative style, you tell the code HOW you want it done
// you manually cache the local variables at the start of the function like a storage to then figure out what is going on
// with those variables and what you want them to become or do and then increment them along the way, figure out what the operation
// is doing, what each operation is doing, whether it's a statement or an expression, what is returning and then storing that
// and essentially becoming the compiler to interpret this
fun itDoesSomething(elements: List<String>): HashMap<String, Int> {
    var i = 0
    val results = hashMapOf<String, Int>()
    while (i < elements.size) {
        val element = results[elements[i]]
        if (element != null) {
            results[elements[i]] = element + 1
        } else {
            results[elements[i]] = 1
        }
        i++
    }
    return results
}

// declarative style & functional programming, you tell the code WHAT you want it to do
// you abstract the functionality into separate, smaller functions with good names
// functional programming is about raising the level of abstraction, making those abstractions essentially functions,
// and then using that to write more expressive, concise and comprehensible code.
fun itDoesSomethingAlso(elements: List<String>): List<Pair<String, Int>> {
    return elements.groupBy {
        it
    }.map {
        Pair(it.key, it.value.count())
    }
}
