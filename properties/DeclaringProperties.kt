package properties

//Properties in Kotlin classes can be declared either as mutable, using the var keyword, or as read-only, using the val keyword.

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

//To use a property, simply refer to it by its name:

fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors called: a result setter accessor is called and an address getter accessor is called
    result.street = address.street // accessors called: a result setter accessor is called and an address getter accessor is called
    // ...
    return result
}