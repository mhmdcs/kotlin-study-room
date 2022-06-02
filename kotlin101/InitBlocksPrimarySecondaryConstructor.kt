package kotlin101

//https://stackoverflow.com/a/55374474/9133569

/**
 * The initializer block i.e. the init block will execute immediately after the primary constructor, thus you could say the initializer blocks effectively become part of the primary constructor.
 *
 * The constructor with the explicit `constructor` keyword is the secondary constructor.
 * The secondary constructor's delegation to the primary constructor is done using the `this` keyword.
 *
 * init block's delegation to the primary constructor happens before the first statement of a secondary constructor, so the code in all init blocks and property initializers is executed before the body of the secondary constructor.
 * Even if the class has no primary constructor, the delegation still happens implicitly, and the initializer block are still executed.
 *
 * The biggest feature is that since the primary constructor cannot contain any code, init blocks allow adding code to the primary constructor.
 * */
        //this is the primary constructor, it gets executed first
class Sample(var s : String) {

    //this is the secondary constructor, it gets executed last
    constructor(a: String, b: String): this(a) {
        this.s += b
    }

    //this is init block, it gets executed immediately after primary constructor
    init {
        s += "C"
    }

    //this is a property initializer, it gets executed immediately after init blocks
    val alphabet = s
}

fun main(){
    val sample = Sample("A","B")
    println(sample.s) //A in the primary constructor executed first, C in the init block executed immediately after, B in the secondary constructor executed last

    println(sample.alphabet) //init blocks and property initializer are executed before the secondary constructor, thus B doesn't get printed here
}