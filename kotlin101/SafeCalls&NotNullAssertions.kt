package kotlin101

//https://stackoverflow.com/a/44248897/9133569

/**To understand ? and !! operators better, say you have this function:*/
//fun delegated_properties.main() {
//    val name: String = null
//    println(name)
//}
/**This will produce the following error at compile at line 2:
 Null can not be a value of a non-null type String */

/**Now, you can prevent that by adding a safe-call operator to the String type to make it nullable*/
//fun delegated_properties.main() {
//    val name: String? = null
//    println(name)
//}
/**This produces a result of: null*/

/**Now, if we want the function to throw an NPE exception when the value of name is null, we can add not-null assertion operator at the end of name: */
//fun delegated_properties.main() {
//    val name: String? = null
//    println(name!!)
//}
/** This will throw a NullPointerException at runtime. */

