package reflection

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

// using reflection to access a class's private member properties and functions at runtime, without knowing them at compile time.

fun main() {

    //   val method: Method = Class.forName("android.os.ServiceManager").getMethod("getService", String.javaClass)
    //   val result : Unit = method.invoke(null, "My_SERVICE_NAME") as Unit

    // we use this instance of the class with private members to invoke functions and get properties from.
    val someClassWithPrivates = SomeClassWithPrivates()

    // accessing the class's private function using Kotlin's reflections:
    SomeClassWithPrivates::class.declaredMemberFunctions.find {
        it.name == "noOneCanAccessMeEither"
    }?.let {
        it.isAccessible = true
        it.call(someClassWithPrivates)
    }

    // accessing the class's private property using Kotlin's reflections:
    someClassWithPrivates::class.memberProperties.find {
        it.name == "noOneCanAccessMe"
    }?.let {
        it.isAccessible = true
        println(it.getter.call(someClassWithPrivates))
    }

    // accessing the class's private method using Java's reflections:
    val method = someClassWithPrivates::class.java.getDeclaredMethod("noOneCanAccessMeEither2", String::class.java)
    method.isAccessible = true
    val parameters = arrayOfNulls<Any>(1)
    parameters[0] = "testing java reflection!"
    method.invoke(someClassWithPrivates, *parameters)

    // accessing the class's private field using Java's reflections:
    val field = someClassWithPrivates::class.java.getDeclaredField("noOneCanAccessMe")
    field.isAccessible = true
    val privateField = field.get(someClassWithPrivates)
    println(privateField)

    // using extension functions to access private functions and properties using Kotlin's reflection API:
    someClassWithPrivates.callPrivateFunction("noOneCanAccessMeEither")
    someClassWithPrivates.callPrivateFunction("noOneCanAccessMeEither2", "testing kotlin reflection!")
    val iAccessPrivateProperties = someClassWithPrivates.getPrivateProperty<SomeClassWithPrivates, String>("noOneCanAccessMe")
    println(iAccessPrivateProperties)

    // using extension functions to access private methods and fields using Java's reflection API:
    someClassWithPrivates.callPrivateMethod("noOneCanAccessMeEither")
    val iAccessPrivateFields = someClassWithPrivates.getPrivateField<String>("noOneCanAccessMe")
    println(iAccessPrivateFields)

}

class SomeClassWithPrivates() {
    private val noOneCanAccessMe = "hee hee"
    private fun noOneCanAccessMeEither() = println("hoo hoo")
    private fun noOneCanAccessMeEither2(args: String) = println("hoo hoo - $args")
}

// extension functions to access private functions and properties using Kotlin's reflection API:
inline fun <reified T> T.callPrivateFunction(name: String, vararg args: Any?): Any? =
    T::class
        .declaredMemberFunctions
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.call(this, *args)

inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R? =
    T::class
        .memberProperties
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.get(this) as? R

// extension functions to access private methods and fields using Java's reflection API:
inline fun <reified T> T.callPrivateMethod(name: String, vararg args: Any?): Any? {
    val classArray: Array<Class<*>> = args.map { it!!::class.java}.toTypedArray()
    return T::class.java.getDeclaredMethod(name, *classArray)
        .apply { isAccessible = true }
        .invoke(this, *args)
}

fun <T> Any.getPrivateField(name: String): T {
    val field = this::class.java.getDeclaredField(name)
    field.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    return field.get(this) as T
}

// difference between declared and non-declared reflections functions:
// getField() can get a field inherited from a superclass, but getDeclaredField() cannot. getDeclaredField() restrict itself to the class you call the function on, however getField() cannot access private members.
// getFields() gives you access to all the public fields up the entire class hierarchy.
// getDeclaredFields() gives you access to all the fields, regardless of their accessibility but only for the current class, not any base classes that the current class might be inheriting from.

//getDeclaredMethods includes all methods declared by the class itself, whereas getMethods() returns only public methods,
// and also those inherited from the base/super class.