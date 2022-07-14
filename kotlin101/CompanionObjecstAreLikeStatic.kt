package kotlin101

//Classes in Kotlin are `final` by default, they can't be inherited from, unless `open`, `abstract`, `interface` modifiers were used.
//Classes with private primary constructor cannot be instantiated (you cannot create objects from them).
class MySystem private constructor() {

    //instance variables (or can be called object variable) are fields that belong to a particular instance of an object.
    //static variables (or can be called class variable) are common to all the instances of the same class. A static variable belongs to the whole class, and there is only one of it, regardless of the number of objects(instances).

    //Any method and property inside companion object act like we added `static` modifier to each of them.
    //They can be called from the class without needing to instantiate it. We sometimes add an optional @JvmStatic annotation above every method/variable inside a companion object so that if someone called our Kotlin code in a Java class, they don't need to write MySystem.Companion.getCurrentYear(), and instead can just call it with MySystem.getCurrentYear()
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