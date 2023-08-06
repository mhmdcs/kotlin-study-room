package delegates

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// https://medium.com/tompee/idiomatic-kotlin-property-delegates-and-lazy-11207213a788

// Property delegation acts the same way as class delegation but this time, on Kotlin properties.
// The class owning the property can delegate the property accessor calls to a delegate object, instead of handling it all by itself.
// There are useful benefits in using property delegation, the most common being lazy initialization.
// Lazy initialization allows you to defer creation of objects until the moment they are needed. Remember the ViewBinding delegate that you use in Android dev, it defers the creation and destruction of ViewBinding objects until they're needed/no longer needed aka between the Fragment View's onViewCreated() and onDestroyView() callbacks :)

// To understand property delegation, understand that a property in Kotlin is composed of accessor methods and an optional backing field.
// These methods, called get() and set(), are automatically generated for you if you do not override them.
// When implementing a property delegate, be aware that the aforementioned get() and set() methods are mapped to the getValue() and setValue() operator functions that we must call within our propery delegate.
// But there's also another way to implement getValue() and setValue() other than calling the operator functions, there are predefined property delegate interfaces that we can use such as ReadOnlyProperty for val,
// and ReadWriteProperty for var, which have getValue() and setValue() methods that we can override and implement, we can either implement the interfaces or the operator functions, both serve the same purpose.

// The initialization of the name property is delegated to an instance of the NamePropertyDelegate class.
// Lets examine the NameDelegate class up close.
fun main() {
    // this line reads: delegated property name of type String initialized by property delegate NamePropertyDelegate object.
    val name: String by NamePropertyDelegate()
    println(name)
}

// NameDelegate class implements the ReadOnlyProperty interface. The ReadOnlyProperty interface overloads the getValue() operator method for us, so we wont have to define the operator keyword on our own.
// getValue() has two parameters, an instance of the reference class in which the delegated property is defined. Here, it is a generic type so it can be usable for different classes.
// The second parameter is of type KProperty. KProperty is a class that represents a Kotlin property. Here we won't care too much about these two parameters and we'll just return a String.
// Notice that the custom delegate has no backing field. If you want to store the current value, you have to define a backing field explicitly. Our example does not need a backing field because the getValue() is delegated to a String "Mohammed".
class NamePropertyDelegate<T> : ReadOnlyProperty<T, String> {
    override fun getValue(thisRef: T, property: KProperty<*>): String = "Mohammed"
}

// same class above, but with directly overloading the operator function getValue() instead of implementing ReadOnlyProperty interface.
//class NamePropertyDelegate {
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "Mohammed"
//}

// Kotlin also provides a read/write delegate that can execute a lambda when the setValue() method is invoked. This “observable” delegate is useful for cases such as data-binding and reactive programming. The syntax for this delegate is as follows:
class ObservableDelegate {
    var hero : Hero by Delegates.observable(initialValue = Hero("agility", "melee")) { property, oldValue, newValue ->
        /* Do something here in this callback*/
    }
}