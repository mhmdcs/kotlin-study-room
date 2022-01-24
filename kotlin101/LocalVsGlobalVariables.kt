package kotlin101

class myClass{

    //var myGlobalVariable1: String  // uninitialized global variables are not allowed. Results in compilation error if uncommented.

    lateinit var myGlobalVariable2: String // `lateinit` uninitialized global variables are allowed. Will result in runtime exception if accessed while it hasn't been initialized yet.

    fun myFunction(){

        var myLocalVariable : String // local variables are allowed to be uninitialized.

    }

}