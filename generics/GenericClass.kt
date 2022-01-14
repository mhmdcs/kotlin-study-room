package generics

//GenericClass, look for classes UngenericClass for a much more limited implementation without generics
//unlike the previous UngenericClass which only worked with Ints, this works with all types, Int, String, etc everything.
//this class accepts in its constructor an array of type T generic
class ArrayUtilGeneric<T> (private val array: Array<T>){
    //inside this class we will define a function that finds a specific element in the supplied array
    //this function will be a higher-order function that takes as its first parameter a T generic element
    //and its 2nd parameter will be a function type which will be called back.
                                //foundElement is a function type that takes index Int and element T generic its parameters and returns nothing (Unit)
                                                        //we will make element nullable with ? safe-call operator because we want to pass null when the  element is not found
    fun findElement(element: T, foundElement: (index: Int, element: T?) -> Unit ){
        for(i in array.indices){
            if(array[i] == element){
                foundElement(i, element)
                return //return to break the loop
            }
        }
        foundElement(-1, null)//since we marked element: Int as nullable with ? safe-call operator we can pass null here
        return
    }
}

fun main(){
    val arrayUtil = ArrayUtilGeneric(arrayOf(1,2,3,4,5))
                                    //since the second parameter takes in a function type, we can call it as a trailing lambda expression outside the higher-order function parenthesis
    arrayUtil.findElement(5) { index, element ->
        println("Element $element was found at index $index")
    }

    val arrayUtilString = ArrayUtilGeneric(arrayOf<String>("Ha","He","Ho"))
    arrayUtilString.findElement("He") { index, element ->
        println("Element $element was found at  index $index")
    }

}