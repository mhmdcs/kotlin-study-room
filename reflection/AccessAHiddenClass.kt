package reflection

fun main() {
    val clazz = Class.forName("reflection.AccessAHiddenClass")  // Always remember that for reflection to work, you will be required to provide the fully-qualified name of the class, which includes its package name.
//    val clazz = AccessAHiddenClass::class.java // also works
    val method = clazz.getMethod("someStaticFunction", String::class.java, Int::class.java)
    val result = method.invoke(null, "Mohammed", 25) as String // we cast invoke()'s result to someStaticFunction's return value
    println(result)
}

private class AccessAHiddenClass {
    companion object {
        @JvmStatic
        fun someStaticFunction(name: String, age: Int) = "my name is $name, I'm $age years old"
    }
}

// https://stackoverflow.com/a/63217305/9133569
// https://stackoverflow.com/q/23679246/9133569