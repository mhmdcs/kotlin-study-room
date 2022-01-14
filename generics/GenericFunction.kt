package generics

//GenericFunction, look for classes UngenericClass for a much more limited implementation without generics
//unlike the previous UngenericClass which only worked with Ints, this works with all types, Int, String, etc everything.

    //we will define a function that finds a specific element in an array
    //this function will be a higher-order function that takes as its first parameter a T generic array
    //and its 2nd parameter will be a T generic element, and its 3rd parameter  will be a function type which will be called back.
                                                     //foundElement is a function type that takes index Int and element T generic its parameters and returns nothing (Unit)
                                                                            //we will make element nullable with ? safe-call operator because we want to pass null when the  element is not found
    fun <T> findElement (array: Array<T>, element: T, foundElement: (index: Int, element: T?) -> Unit ){
        for(i in array.indices){
            if(array[i] == element){
                foundElement(i, element)
                return //return to break the loop
            }
        }
        foundElement(-1, null)//since we marked element: Int as nullable with ? safe-call operator we can pass null here
        return
    }

fun <T, R> justForTesting(t: T, r:  R) = println("T value is  $t, R value is $r")

fun main(){
    val array = arrayOf(1,2,3,4,5)
                                   //since the second parameter takes in a function type, we can call it as a trailing lambda expression outside the higher-order function parenthesis
    findElement(array, 5) { index, element ->
        println("Element $element was found at index $index")
    }

    val arrayString = arrayOf<String>("Ha","He","Ho")
    findElement(arrayString, "He") { index, element ->
        println("Element $element was found at  index $index")
    }

    justForTesting<String, Int>("Ha",10)

}