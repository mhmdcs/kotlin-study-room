package kotlin101

import java.io.File

fun main() {
    val file = File("/Users/mohammedalmekhlafi/IdeaProjects/KotlinStudyRoom/src/kotlin101/ReadFile.kt")

    // read file line by line by using forEachLine()
    file.forEachLine {
        println(it)
    }

}