package designpatterns

// Proxy pattern
// Proxy pattern provides a substitute or a representative object for another "real" object
// the proxy object controls access to that other "real/original" object, allowing you to perform
// something before or after the request is delegated to the real original object.
// Proxy pattern requires both the real original object and the proxy object to implement the same interface.

interface Internet {
    fun connectTo(host: String)
}

class RealInternet: Internet {
    override fun connectTo(host: String) {
        println("Successfully connected to $host")
    }
}

class ProxyInternet: Internet {
    private val realInternet = RealInternet()
    private val bannedDomain = "banned.com"
    override fun connectTo(host: String) {
        if(host.contains(bannedDomain)) { // controlling access to the real object is what makes Proxy pattern different from Decorator pattern
            println("Access to $bannedDomain denied")
            return
        }
        realInternet.connectTo(host)
    }
}

fun main() {
    val proxyInternet: Internet = ProxyInternet()
    proxyInternet.connectTo("google.com")
    proxyInternet.connectTo("banned.com")
}

// Both the Proxy and Decorator pattern give you the ability to swap objects, i.e. change an original's object's behavior, dynamically at runtime
// rather than changing the original object's class behavior statically at compile-time, both patterns also allows to "nest" objects i.e.
// wrap a wrapper object inside another wrapper object that wraps around the original object, this way, Proxy/Decorator patterns become much
// more flexible than using direct inheritance to subclass a class to give it extra functionalities, say for example you have SubclassA, SubclassB and SubclassC,
// you can't inherit from *both* SubclassA and SubclassC to create a SubclassAC since in inheritance you're only allowed to have one parent, thus you'll be forced
// to repeat yourself by writing code in a new SubclassAC that copy/paste the code in both SubclassA and SubclassC classes.
// while with Proxy/Decorator patterns you can have WrapperA, WrapperB and WrapperC classes, and then wrap WrapperA around WrapperC to get both the additional
// functionalities of WrapperA and WrapperC classes.
// Basically, in the Decorator/Proxy patterns, you can use the wrappers separately, or create different combinations of them, all of this happens dynamically at runtime.

// Quote from GoF book about the benefits of Decorator/Proxy patterns over subclassing.
// Suppose you have a TextView class. Then in some place you want a scrolled text view, so you subclass TextView and create ScrolledTextView class.
// And in some other place you want a border around text view. So you subclass again and create BorderedTextView. Well, now in some place
// you want border and scroll both. None of the previous two sub classes have both capability. So you need to create a 3rd one. When
// creating a ScrolledBorderedTextView you are actually duplicating the effort. You don't need this class if you have any way to
// compose the capability of previous two. Well, things can go worse and these may lead to unnecessary class explosion.