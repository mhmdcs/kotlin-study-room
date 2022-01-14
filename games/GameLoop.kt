package games

fun main(){

    var isAlive = true

    while(isAlive){
        print("What's on your mind? (q to quit) ")
        val input = readLine()

        println("$input? Interesting.")

        if(input == "q")
            isAlive = false
    }

}