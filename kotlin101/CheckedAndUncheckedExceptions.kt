package kotlin101

import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

// In Java
// Checked exceptions are compile-time exceptions that you must handle at compile time or your program won't run
// Checked exceptions in Java are indicated using the throws keyword, and you must indicate a class (or multiple classes) that could be thrown by a method, in its signature i.e.: throws IOException, InterruptedException, ParseException
// Unchecked exceptions are runtime exceptions that the compiler won't yell at you for not handling, but you might face them at runtime
// Unchecked exceptions are only exceptions from classes that extend RuntimeException, like NullPointerException.
// Unchecked exceptions in Java are thrown with the throw keyword, and you must throw only one object, in the method body i.e.: throw new RuntimeException()

// Kotlin does NOT have checked exceptions
// As a matter of fact, Kotlin doesn't even have a throws clause at all.

fun main() {
    readFile("myFile.txt")
}

@Throws(FileNotFoundException::class) // We don't need this at all if our codebase is purely Kotlin and we aren't developing a library. It's just for Java Interoperability, since Kotlin doesn't have throws keyword nor checked exceptions, we can annotate Kotlin methods with @Throws() so that when our Kotlin methods are called from Java code, they have to handle it at compile-time just like they're used to.
fun readFile(fileName: String) {
        // FileReader() constructor declares checked exception FileNotFoundException in constructor signature, if we were in Java we would have to catch it at compile-time
        // but since Kotlin doesn't have checked exceptions, it'll be treated as a "thrown exception" and not a "declared exception", there won't be any compiler-errors and our code will crash at runtime.
       val fileReader = FileReader(fileName)
       fileReader.read()
}

// Kotlin treats all Java exception times the same, whether they're checked (compile-time) or unchecked (runtime) exceptions
// There won't be any compiler errors for either, and both will be thrown at runtime
// ALL exceptions are basically considered unchecked exceptions.
fun throwJavaUnchecked() {
    throw IllegalArgumentException()
}

fun throwJavaChecked() {
    throw IOException()
}