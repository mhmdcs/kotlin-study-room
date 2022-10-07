package reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions

fun main() {
    // calling a private class's static function using Java reflection
    val clazz = Class.forName("reflection.AccessAHiddenClass")  // Always remember that for reflection to work, you will be required to provide the fully-qualified name of the class, which includes its package name.
  //  val clazz = AccessAHiddenClass::class.java // also works
    val method = clazz.getMethod("someStaticFunction", String::class.java, Int::class.java)
    val result = method.invoke(null, "Mohammed", 25) as String // we cast invoke()'s result to someStaticFunction's return value
    println(result)

    // calling a private class's static function using Kotlin reflection
   // val klass = Class.forName("reflection.AccessAHiddenClass").kotlin // https://stackoverflow.com/a/34340492/9133569
    val klass = AccessAHiddenClass::class.companionObject
    val kompanionObject = AccessAHiddenClass::class.companionObjectInstance
    klass?.functions?.find {
        it.name == "someStaticFunction"
    }?.let {
        val kResult = it.call(kompanionObject, "Mohammad", 25)
        println(kResult)
    }
}

private class AccessAHiddenClass {
    companion object {
        @JvmStatic
        fun someStaticFunction(name: String, age: Int) = "my name is $name, I'm $age years old"
    }
}

// https://stackoverflow.com/a/63217305/9133569
// https://stackoverflow.com/q/23679246/9133569