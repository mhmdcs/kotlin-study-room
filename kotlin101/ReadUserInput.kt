package kotlin101

fun main() {
    println("What is your first" +
            " name?")
    val name = readLine() //equivalent to using Scanner class in Java, but much shorter!

    println("What is your age?")
    val age = Integer.parseInt(readLine())

    println("User first name is $name and their age is $age")
}