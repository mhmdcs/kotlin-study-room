package games

fun main(){

    //print 10 by 10 grid
    //nest two for loops that act as x and y coordinates
    repeat(10){ x ->
        repeat(10){ y ->
            val text = if (x == 0 ||  x == 9 || y == 0 || y == 9 || x == y || y + x == 9) "# " else "  "
            print(text)
        }
        println() //provide line separation after each row by printing a new line
    }

}

//val text = if (x == 0 ||  x == 9 || y == 0 || y == 9) "# " else "  " //get the edges of the x coordinate by checking if they're equal to 0 or 9, and same thing for y coordinate, so if we're in any of those positions, return # with a space after it, otherwise return two spaces
//val text = if (x == 0 ||  x == 9 || y == 0 || y == 9 || x == y || y + x == 9) "# " else "  " //like the above version, but with the diagonals crossing