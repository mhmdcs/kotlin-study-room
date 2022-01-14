package kotlin101

fun main(){
                    //named arguments allow you to explicitly name the arguments, good for readability's sake, but they're optional, a neat feature of them is that they allow you to change the order of the arguments
    val mhmd = Person(age = 24, isStudent = true, name = "Mohammed")
    println(mhmd)

    val mhmdClone = mhmd.copy(age = mhmd.age+1)//since this is a clone, we can increment the age by constructing it again, despite age being val (read-only)
    println("The birthday boi: $mhmdClone")//this is called String Interpolation
}

data class Person(
    val name: String,
    val age: Int,
    val isStudent: Boolean = false //this is called a default value, which  means that isStudent can be skipped when constructing new objects of Persons because it has a default value of false, and it can be overridden by true.
)