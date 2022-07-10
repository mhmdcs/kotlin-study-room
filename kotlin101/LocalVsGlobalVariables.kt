package kotlin101

const val a = "A" // global variable, can be accessed from anywhere in the program, for a variable to be global it has to be defined outside a function scope.

class myClass{

    // all member variables are global variables, but not all global variables are member variables.
    // member variables are variables that are associated with a specific object of a class, they can only be declared inside a class's scope. Unless it’s static, there will be a different copy of the variable for every object of that class.
    // each object created from the same class will have their own unique member variables values. If we want a variable to have the same value throughout all instances of a class then we can declare it as a static variable in our program.

    //var myGlobalVariable1: String  // uninitialized global variables are not allowed. Results in compilation error if uncommented.

    lateinit var myGlobalVariable2: String // global member variable. Uninitialized global variables are not allowed unless they were marked as `lateinit`. Will result in runtime exception if they were accessed while they haven't been initialized yet. Will result in compiler time error if `lateinit` modifier was removed

    fun myFunction(){

        var myLocalVariable : String // local variable. Local variables are allowed to be uninitialized, unlike global variables.

    }

}