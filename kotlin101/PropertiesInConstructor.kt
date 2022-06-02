package kotlin101

//https://stackoverflow.com/a/45822027/9133569

//Constructor parameters must use var/val when they are used as a property elsewhere in the class.
//They do not need to be properties if they are only used for class initialization.

//In class A example, the parameter must be a property (var or val) because it is used inside a function. Also, if you want to modify the value of a constructor property, it must be var.
class A(var number: Int) {
    fun myFunction(){
        number = 4
        println("number is a property that can be used within functions, it can only do that because it was defined as a property in the constructor $number")
    }
}

//In class B example, the parameter is only used to initialize the class, so it does not need to be a property.
//it is simply a parameter passed to the primary constructor, where it can be used it to initialize class properties, or to accessed within an init block.
class B(number: Int) {
    val value = number
    init {
        println("number is just a parameter passed that can only be accessed within init block or, or used to initialize class properties like value $number")
    }
}