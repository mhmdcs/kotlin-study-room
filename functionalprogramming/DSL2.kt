package functionalprogramming

// https://medium.com/tompee/idiomatic-kotlin-lambdas-with-receiver-and-dsl-3cd3348e1235

// extension function types AKA lambdas with receivers AKA Kotlin internal DSL

// with the power of objects invocation, function types, and extension functions, we can create a DSL out of any object in Kotlin.

fun main() {
    val xmlWithoutDSL = encloseInXMLAttribute(StringBuilder(), "attr") {
        it.append("MyAttribute")
    }
    println(xmlWithoutDSL)

    // with DSL, this allows us to use call the receiver StringBuilder object reference directly, without any qualifiers (i.e. we can access all the public member functions and variables of the receiver without `this` or anything)
    val xmlMiniDSL = encloseInXMLAttributeMiniDSL(StringBuilder(), "attr") {
        append("MyAttribute")
    }
    println(xmlMiniDSL)

    val xmlDSL = createAndroidLayout()
        .child("LinearLayout") {
            attr("android:layout_height", "match_parent")
            attr("android:layout_width", "match_parent")
            attr("android:orientation", "vertical")
            child("ImageView") {
                attr("android:layout_height", "match_parent")
                attr("android:layout_width", "match_parent")
            }
            child("TextView") {
                attr("android:layout_height", "match_parent")
                attr("android:layout_width", "match_parent")
            }
        }
    println(xmlDSL.data())
}

// without DSL, in the action function type parameter, we simply take in a StringBuilder as a parameter.
fun encloseInXMLAttribute(sb: StringBuilder, attr: String, action: (StringBuilder) -> Unit): String {
    sb.append("<$attr>")
    action(sb)
    sb.append("</$attr>")
    return sb.toString()
}

// with DSL, we use action function type as an extension function to the StringBuilder class.
fun encloseInXMLAttributeMiniDSL(sb: StringBuilder, attr: String, action: StringBuilder.() -> Unit): String {
    sb.append("<$attr>")
    sb.action() // we could also call it with the action(sb) syntax :) by using StringBuilder as an extension function receiver, we signified to the compiler that we will be receiving an instance of that type as the first parameter.
    sb.append("</$attr>")
    return sb.toString()
}

// creating a simple Android view xml layout generator using Kotlin DSL
// We will try to create an XML generator whose syntax closely resembles XML element hierarchy, something like this.
/**
  XML.child
        .attribute
        .attribute
        .child
            .attribute
            .attribute
        .child
            .attribute
            .attribute
 */

// First let us create an XMLContainer class. This will represent our outermost container.

open class XMLContainer {
    private val data = StringBuilder()

    fun data() = data.toString()

    // Our container has only 1 function named child() that accepts as a last parameter a lambda with a receiver of type XMLChildContainer.
    // This means that our outermost container, XMLContainer, can only have children of type XMLChildContainer, nothing more. This is one of the benefits of internal DSLs. You can enforce specific rules to verify the correctness of the output.
    fun child(tag: String, action: XMLChildContainer.() -> Unit): XMLContainer {
        data.append("<$tag")
        val tagData = XMLChildContainer()
        tagData.action()
        data.append(tagData.attrData())
        data.append(">")
        data.append(tagData.attrData())
        data.append("\n<$tag>")
        return this
    }

}

class XMLChildContainer: XMLContainer() {
    private val tagData = StringBuilder()

    fun attrData() = tagData.toString()

    fun attr(attrName: String, value: String) {
        tagData.append("$attrName=\"$value\"")
    }
}

// XMLContainer factory method
fun createAndroidLayout(): XMLContainer {
    return XMLContainer()
}