package random_misc

//https://www.javatpoint.com/java-string-format
//The % percentage sign called the "format operator" or "format specifier" is used to denote the data type and its output.
fun main(){

    val name = "Mohammed"
    println(String.format("My name is %s", name)) //prints: My name is Mohammed
    println(String.format("My name is %s \nGlad to meet you!", name)) //prints: My name is Mohammed
                                                                              //Glad to meet you!

    val number = 43.5189
    println(String.format("Value is %f", number)) //prints: Value is 43.518900
    println(String.format("Value is %.0f", number)) //prints: Value is 44
    println(String.format("Value is $%.1f", number)) //prints: Value is $43.5

}