package properties

//You can define custom accessors for a property. If you define a custom getter, it will be called every time you access
//the property (this way you can implement a computed property). Here's an example of a custom getter:

class Rectangle(val width: Int, val height: Int) {
    val area: Int //type is optional here since it can be inferred from getter, it can be omitted in this case
        get() = this.width * this.height //here we defined custom getter accessor
//        get() { return this.width * this.height } //defining getter method using classic curly braces and return, instead of the = shorthand
}

fun main() {
    val rectangle = Rectangle(3, 4)
    println("Width=${rectangle.width}, height=${rectangle.height}, area=${rectangle.area}") //we used our custom getter in rectangle.area
}
