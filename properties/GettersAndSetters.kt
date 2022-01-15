package properties

//The full syntax for declaring a property is as follows:
/**
var <propertyName>[: <PropertyType>] [= <property_initializer>]
[<getter>]
[<setter>]
 */

//The initializer, getter, and setter are optional. The property type is optional if it can be inferred from the initializer
// or the getter’s return type, as shown below:

var initialized = 1 // has type Int, default getter and setter
// var allByDefault // ERROR: explicit initializer required, default getter and setter implied

//The full syntax of a read-only property declaration differs from a mutable one in two ways: it starts with val instead of var and does not allow a setter:
val inferredType = 1 // has type Int and a default getter