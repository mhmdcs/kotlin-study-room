package kotlin101

//Jump statements are a type of Control Statements in programming languages used to interrupt the normal flow of the program.
//It makes the program jump to another section of the program unconditionally when encountered. It can also be used to terminate any loop, and sometimes even terminate functions.
//In C/C++ there are four jump statement: continue, break, return, and goto.
//In Java there are three jump statements: break, continue, return. Java also has "labels" in place of "goto" for loops. It is still a reserved as a keyword just in case they wanted to add it to a later version.
//In Kotlin there are three jump expressions: return, break, continue. Kotlin also has "labeled returns" in place of "goto".
//In Kotlin: `return` by default returns from the nearest enclosing function or anonymous function. Which means that `return` exits the nearest current function.
//In Kotlin: `break` terminates the nearest enclosing loop.
//In Kotlin: `continue` proceeds to the next step of the nearest enclosing loop.

//Labeled Returns
//https://kotlinlang.org/docs/returns.html#return-to-labels

//In short
//`return` statement ends the execution of a function, and returns control to the calling function. Execution resumes as normal in the calling function at the point immediately following the call. A `return` statement can also return a value to the calling function.
//When you read that "`return` statement returns from the current function" it just means "`return` statement exits the current function". Note: Always remember that lambdas are anonymous callback functions, that are passed inside higher-order functions :)

/**delegated_properties.Example A*/
/**In Kotlin, functions can be nested using function literals, local functions, and object expressions.
 * Qualified `return`s allow us to return from an outer function. The most important use case is returning from a lambda expression.
 * Recall that when we write the following, the `return`-expression returns from the nearest enclosing function - `foo`:
 * */
///**Note that such non-local returns are supported only for lambda expressions passed to inline functions. Like the forEach() inline function.*/
//fun foo() {
//    listOf(1, 2, 3, 4, 5).forEach {
//        if (it == 3) return // non-local return directly to the caller of foo(). Prints concatenation of 1 and 2, then returns to caller of foo() at 3.
//        print(it)
//    }
//    println("this point is unreachable")
//}
//fun delegated_properties.main() {
//    foo()
//}

/**delegated_properties.Example B*/
/**To return from a lambda expression, label it and qualify the `return`*/
/**Now, it returns only from the lambda expression.*/
//fun foo() {
//    listOf(1, 2, 3, 4, 5).forEach heeHoo@{
//        if (it == 3) return@heeHoo // local return to the caller of the lambda - the inline fun forEach() loop.
//        print(it)
//    }
//    print(" done with an explicit label")
//}
//fun delegated_properties.main() {
//    foo()
//}

/**delegated_properties.Example C*/
/**Often it is more convenient to use implicit labels,
 *because such an implicit label has the same name as the function to which the lambda is passed into.*/
//fun foo() {
//    listOf(1, 2, 3, 4, 5).forEach {
//        if (it == 3) return@forEach // local return to the caller of the lambda - the inline fun forEach() loop.
//        print(it)
//    }
//    print(" done with implicit label")
//}
//fun delegated_properties.main() {
//    foo()
//}

/**delegated_properties.Example D*/
/**Alternatively, you can replace the lambda expression with an anonymous function. A `return` statement in an anonymous function
 * will return from the anonymous function itself. This is unlike the first example where `return` returned directly to the caller of foo().
 */
//fun foo() {
//    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
//        if (value == 3) return  // local return to the caller of the anonymous function - the forEach() loop function.
//        print(value)
//    })
//    print(" done with anonymous function")
//}
//fun delegated_properties.main() {
//    foo()
//}
/**Note that the use of local `return`s in the examples B,C,D is similar to the use of `continue` in regular loops.
 * */


/**There is no direct equivalent for `break`, but it can be simulated by adding another nested lambda
 * and non-locally returning from it:
 */

/**delegated_properties.Example E*/
fun foo() {
    run nest@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@nest // non-local return to the caller of forEach() - the nest@ labeled lambda passed into run
            print(it)
        }
    }
    print(" done with nested lambda")
}
fun main() {
    foo()
}
