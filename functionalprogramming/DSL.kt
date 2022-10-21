package functionalprogramming

// extension function types AKA lambdas with receivers AKA Kotlin internal DSL

// with the power of invoking objects, function types, and extension functions, we can create a DSL out of any object.
class Port(var value: Int = 0, var isSecure: Boolean = false) {
    operator fun invoke(function: Port.() -> Unit) = function()
}
class Configuration(var host: String = "", var port: Port = Port()) {
    operator fun invoke(function: Configuration.() -> Unit) = function()
}

fun main() {

    val config = Configuration()

    config {
        host = "127.0.0.1"
        port {
            value = 90
            isSecure = true
        }
    }

    println("Configuration host = ${config.host} | port value = ${config.port.value} | port status = ${config.port.isSecure}")

}