package InterfaceVsAbstract

//Abstract classes can have state with instance variables.
//This means that an instance variable can be used and mutated. Here's an example, variable *name* can hold a state.
abstract class Animal4 {
     var name = "animal" // abstract classes can store states with instance variables.
    // abstract var name2 = "animal" //abstract classes CANNOT store states with abstract variables.
}

class Fox4: Animal4()

fun main(){
    val fox: Animal4 = Fox4()
    fox.name = "mutated animal" // we were able to mutate(modify) the state of thee abstract class's variable
    println(fox.name)
}

//Interfaces cannot store states(fields).
interface Animal5 {
    // var name = "animal"  // will result in a compiler error. Interfaces CANNOT store states AT ALL.
    var name2: String // it's OK in interfaces to declare a property without a state. Remember that all properties and methods in interfaces are implicitly `abstract` by default.
}