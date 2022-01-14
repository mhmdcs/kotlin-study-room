package generics

//UngenericClass, look for classes GenericClass and GenericFunctions to see a much better implementation of this with generics

                //this class accepts in its constructor an array of type Int
class ArrayUtil(private val array: Array<Int>){
    //inside this class we will define a function that finds a specific element in the supplied array
    //this function will be a higher-order function that takes as its first parameter the integer element
    //and its 2nd parameter will be a function type which will be called back.
                                //foundElement is a function type that takes two Int in its parameters and returns nothing (Unit)
                                                            //we will make element nullable with ? safe-call operator because we want to pass null when the  element is not found
    fun findElement(element: Int, foundElement: (index: Int, element: Int?) -> Unit ){
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
    val arrayUtil = ArrayUtil(arrayOf(1,2,3,4,5))
                                    //since the second parameter takes in a function type, we can call it as a trailing lambda expression outside the higher-order function parenthesis
    arrayUtil.findElement(5) { index, element ->
        println("Element $element was found at index $index")
    }
}