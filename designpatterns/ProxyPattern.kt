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