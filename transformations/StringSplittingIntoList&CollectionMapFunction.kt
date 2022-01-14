package transformations

fun main(){
    val scores = "34::54::63::97::43::23"

    val scoresList: List<String> = scores.split("::")

    println("Scores list in String: $scoresList")
    println("Scores list in String 4th element(3rd index): ${scoresList[3]}")
    println("Scores list in String 4th element(3rd index) when we add 3 to it: ${scoresList[3]+3}")

    val scoresListInt = scoresList.map {
        it.toInt()
    }

    println("Scores list in Integer: $scoresListInt")
    println("Scores list in Integer 4th element(3rd index): ${scoresListInt[3]}")
    println("Scores list in Integer 4th element(3rd index) when we add 3 to it: ${scoresListInt[3]+3}")


}