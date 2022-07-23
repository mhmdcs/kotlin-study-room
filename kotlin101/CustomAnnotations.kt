package kotlin101

/*Annotations are means of attaching metadata to code. To declare an annotation, put the `annotation` modifier in front of a class:
annotation class Fancy

Additional attributes of the annotation can be specified by annotating the annotation class with meta-annotations:

@Target specifies the possible kinds of elements which can be annotated with the annotation (such as classes, functions, properties, and expressions);

@Retention specifies whether the annotation is stored in the compiled class files and whether it's visible through reflection at runtime (by default, both are true);

@Repeatable allows using the same annotation on a single element multiple times;

@MustBeDocumented specifies that the annotation is part of the public API and should be included in the class or method signature shown in the generated API documentation.*/

// side note: for some info about how Class/KClass works in relation to Java/Kotlin and reflecting with the JVM read: https://stackoverflow.com/a/56176620/9133569
// here we'll explore the usage of custom annotations in Kotlin, with our own @Smart annotation.
fun main() {
    val watch = Watch::class // we use class reference :: reflection operator to create a KClass instance that represents our Watch class to introspect its metadata, such as whether annotations are present on it or not. Note: alternatively we could've called Watch().javaClass.kotlin instead of Watch::class

    println(watch.annotations) // if we set @Retention to AnnotationRetention.RUNTIME, the annotation will be visible at runtime for reflection and this method will return a value, but if we set it to AnnotationRetention.SOURCE, it'll only be visible at compile time and thus this method will return an empty [] as the JVM now thinks that this class doesn't have any annotations.
}

@Smart("WearOS")
class Watch {
    // @Smart since we specified the annotation target as AnnotationTarget.CLASS, @Smart annotation will not work on functions, unless AnnotationTarget.FUNCTION was specified.
    fun setAlarm() {

    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
//@Repeatable // allows us to repeat the annotation on a given element, if we try to add another @Smart annotation to our Watch class, it'll result in a compiler error, but if we annotate our annotation class with @Repeatable, we'll be able to use multiple @Smart for our Watch class. Note that @Repeatable only works when @Retention is set at AnnotationRetention.SOURCE :)
@MustBeDocumented // if you have a public API (like a library) you must include the annotation signature in the generated documentation, you can do this by annotating your annotation with @MustBeDocumented, otherwise it's necessary
annotation class Smart(val os: String)