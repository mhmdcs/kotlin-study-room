package kotlin101

/*Annotations are means of attaching metadata to code. To declare an annotation, put the `annotation` modifier in front of a class:
annotation class Fancy

Additional attributes of the annotation can be specified by annotating the annotation class with meta-annotations:

@Target specifies the possible kinds of elements which can be annotated with the annotation (such as classes, functions, properties, and expressions);

@Retention specifies whether the annotation is stored in the compiled class files and whether it's visible through reflection at runtime (by default, both are true);

@Repeatable allows using the same annotation on a single element multiple times;

@MustBeDocumented specifies that the annotation is part of the public API and should be included in the class or method signature shown in the generated API documentation.*/

// here we'll explore the usage of the default, built-in annotations in Kotlin, such as the @Deprecated annotation.
fun main() {
    val phone = Phone()

    phone.camera() // notice the text is strikethrough due to us annotating camera() inside Phone class with @Deprecated

}


class Phone {

    @Deprecated("Instead of this functions use smartCamera()", ReplaceWith("smartCamera()")) // ReplaceWith itself is an annotation. When you supply another annotation as an argument to an annotation, you must omit the @ and directly instantiate the object.
    fun camera(){
        println("Recording")
    }

    fun smartCamera(){
        println("Recording and Detecting")
    }
}