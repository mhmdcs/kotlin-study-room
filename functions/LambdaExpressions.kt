package functions

val sum1: (Int, Int) -> Int = { x, y -> x + y }

// identical to above, just declaring the integer type at a different place
val sum2 = { x: Int, y: Int -> x + y }

