fun main() {

    val ocdDivisionId = "ocd-division/country:us/state:ks"

    val country = ocdDivisionId.substringAfter("country:", "")
        .substringBefore("/")
    val state = ocdDivisionId.substringAfter("state:", "")
        .substringBefore("/")

    println(country)
    println(state)

}