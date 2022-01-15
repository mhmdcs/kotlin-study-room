package properties

//If you want to do something that does not fit into this "implicit backing field" scheme,
// you can always fall back to having a backing property:

private var _table: Map<String, Int>? = null //only accessible to the class it's defined in as a var with a setter and getter

public val table: Map<String, Int> //accessible to the outside world as read-only val with a getter
    get() {
        if (_table == null) {
            _table = HashMap() // Type parameters are inferred
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }