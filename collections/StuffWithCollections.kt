package collections

// buildList() builds a new read-only List by populating a MutableList using the given builderAction, and returns a read-only (i.e. immutable) list with the same elements.
//The mutable list passed as a receiver to the builderAction is valid only inside that function. Using it outside of the function produces an unspecified behavior.
//The returned list is serializable (JVM), which means the List complex object can be converted into a byte-stream, and back into a List complex object, to ease the transfer of this object between  different JVMs, files or network.

fun main() {
    val x = listOf('b', 'c')
    val y = buildList {
        add('a')
        addAll(x)
        add('d')
    }
    println(y)
    println(x)
}