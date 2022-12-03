package delegates

import kotlin.reflect.KProperty

class Delegate {
                //getter, 1st param is the object you read, 2nd param is the property description
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
                //setter, 1st param is the object you read, 2nd param is the property description, 3rd param is the value being assigned
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    //this line says: property variable stringObject of type String is provided by a delegate named Delegate()
    //or another way to read it: the setters and getters of property variable stringObject of type String are delegated(i.e. given) by (function or object/instance) Delegate()
    var stringObject: String by Delegate()
    //stringObject is called "delegated property", and Delegate() is called the "delegate" or "property delegate".
}

fun main() {
    val e = Example()
    println(e.stringObject) //this will call stringObject's getter method getValue() that was provided by the Delegate()
    e.stringObject = "NEW" //this will call stringObject's setter method setValue() that was provided by the Delegate()
}