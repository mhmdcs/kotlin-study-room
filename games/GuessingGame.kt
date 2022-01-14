package games

import kotlin.math.roundToInt

fun main(){

    val secretNumber = (Math.random()*10).roundToInt() //print randomly generated integer numbers from 0-10

    println("What number am I thinking of? from 0 to 10")

    while(true){

        val answer = Integer.parseInt(readLine())

        if(answer == secretNumber) {
            println("Correcto! I was indeed thinking of $secretNumber!")
            return //end the while loop when this condition is met by returning here
        }
        else
            println("Oops! Try again.")

    }
}