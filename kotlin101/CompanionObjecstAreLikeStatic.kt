package kotlin101

//Classes in Kotlin are `final` by default, they can't be inherited from.
//Classes with private primary constructor cannot be instantiated (you cannot create objects from them).
class MySystem private constructor() {

    //Any method and property inside companion object act like we added `static` to each of them.
    //They can be called from the class without needing to instantiate it.
    companion object {
        fun getCurrentYear(){
            println("2021")
        }
    }
    fun unAccessible(){
        println("This function can't be called because it isn't static (i.e. not inside a companion object)")
    }
}

fun main(){
    MySystem.getCurrentYear()
}