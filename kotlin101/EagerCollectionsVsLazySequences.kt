package kotlin101

// Eager Collections (Iterables) vs Lazy Sequences in Kotlin

// Eager evaluation is the opposite of lazy evaluation, it is sometimes known as strict evaluation.
// Eager evaluation means expression is evaluated as soon as it is encountered, unlike lazy evaluation which refers to evaluation of an expression when needed (i.e. accessed for the first time).
// The evaluation strategy in which function arguments are evaluated before the function call is made is known as an "eager" evaluation.

// In Kotlin, collections are by default eager. To make them lazy we need to turn them into Sequences.
// All collections in Kotlin can be converted to a lazy sequence using the asSequence() method.
// In Java 8, Streams are evaluated lazily. This means that no more work than needed will be performed. Kotlin's Sequences mirror Java's Streams.
fun main() {
    val numbers = 1..1000000

    // this is eager evaluation, what that means is that it'll look through the entire list of 1000000 numbers, then filter out all of those that are divisible by 5 (i.e. multipliers of 5), even though at the end of the day all you want is to take the first 30 of those numbers
    numbers.filter { it % 5 == 0 }.take(30)

    // if you want to turn any Iterable / Collection into lazy, then all you need to do is take any class that implements Iterable and call asSequence() on it, as soon as you call asSequence() the whole expression becomes lazily evaluated, so essentially the same as Haskell where everything by default is lazy evaluated
    numbers.asSequence().filter { it % 5 == 0 }.take(30)

    // If you don't have an iterable and still want to lazy evaluate a value then Kotlin has the generateSequence() in the standard library for that.
    generateSequence(1) { it * 5 }.filter { it % 2 == 0 }.take(10)


    val list = listOf(1, 2, 3, 4, 5)

    // Because Collections are eager, this will perform five map() operations, then five filter() operations, and then extract the first value.
    list.map { n -> n * n }.filter { n -> n < 10 }.first()

    // Because Sequences are lazy, this will only perform one map() operation, then and one filter() operation, and then extract the first value. This iis because from the perspective of the last operation, no more evaluation is needed.
    list.asSequence().map { n -> n * n }.filter { n -> n < 10 }.first()


    //////////////////////////////////////////////////////////////////////////////////////////////////////////

// Eager Collections:
//    val myList =  1..1000
//    val result = myList
//        .map{ println("In Map"); it * 2 }
//        .filter { println("In Filter");it % 3  == 0 }
//    println("Before Result")
//    println(result.first())

// Lazy Sequences:
    val myList = 1..1000
    val result = myList.asSequence()
        .map{ println("In Map"); it * 2 }
        .filter { println("In Filter");it % 3  == 0 }
    println("Before Result")
    println(result.first())
}

// When the processing of an Iterable includes multiple steps, they are executed eagerly: each processing step completes
// and returns its result – an intermediate collection. The following step executes on this collection.
// In turn, multi-step processing of sequences is executed lazily when possible: actual computing happens only when the
// result of the whole processing chain is requested.

// The order of operations execution is different as well: Sequence performs all the processing steps one-by-one for every single element.
// In turn, Iterable completes each step for the whole collection and then proceeds to the next step.

// So, the sequences let you avoid building results of intermediate steps, therefore improving the performance of the whole collection
// processing chain. However, the lazy nature of sequences adds some overhead which may be significant when processing smaller collections
// or doing simpler computations. Hence, you should consider both Sequence and Iterable and decide which one is better for your case.

