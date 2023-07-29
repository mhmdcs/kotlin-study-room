package kotlin101

/**
Kotlin bitwise operators vs logical operators

Logical operations assume that the entire variable represents either true or false, combining two integer values using a logical operator produces one result, with the variable representing true or false.

Bitwise operations assume that each bit in the variable's value represents a separate true or false, combining two integer values using a bitwise operator produces 8 (or 16 or 32 or 64) separate bits each representing a true or a false.

Logical operators compare bits of the given object and always return a Boolean result. Logical operators in Kotlin are && (logical and) || (logical or) and ! (logical not):
&& (logical and) returns true if both statements are true.
|| (logical or) returns true if one of the statements is true.
! (logical not) reverse the result, returns false if the result is true.

Bitwise operators perform operations on individual bits, and the result is also always a bit, bitwise operators in Kotlin are and(), or(), xor(), inv().

Yes, we can do bitwise operations in Kotlin.
Despite the fact that the usual symbols, like <<, >>, |, &, and ^, are missing, we can do everything we can in C (or Java),
but with sensibly named functions of classes Int and Long, that make use of Kotlin's `infix` keyword to improve bitwise operations readability.

The Kotlin community has been arguing about bitwise operators for more than ten years.
Those who support the idea say that it’s a requirement of their low-level domains – usually, sound or video processing.
Those who oppose it put other things as more important and also point out that sometimes the code overcrowded with bitwise symbols is completely unreadable in languages that allow them.
https://youtrack.jetbrains.com/issue/KT-1440

Kotlin bitwise operations and their Java counterparts:
Operation Name __ Java Operator __ Kotlin Int/Long Function
Conjunction (and) __ a & b __ a and b
Disjunction (or) __ a | b __ a or b
Exclusive disjunction (xor) __ a ^ b __ a xor b
Inversion (not) __  ~ a __ a.inv()
Shift Left __ a << bits __ a shl bits
Shift Right __ a >> bits __ a shr bits
Unsigned Shift Right __ a >>> bits __ a ushr bits
Note: Unsigned Shift Right is also called "Shift Right Fill Zero" or "Arithmetic Shift Right"

Java also includes assignment operators modified with each of the bitwise operators, like |=. In Kotlin, we will have to repeat ourselves: a = a or b.

The word “and” is conjunctive, meaning it combines things. Conversely, the word “or” is disjunctive, meaning it separates things.

Bitwise operations in Kotlin are represented by functions that can be called in infix form. They can be applied only to Int (32-bits) and Long (64-bits).

Bitwise and bit shift operators are used on only two types (Int and Long) to perform bit-level operations.

Bitwise conjunction, disjunction, and inversion work similarly to their logical counterparts, the difference is that bitwise operators affect each and every bit of the operands separately.
Remember the truth table when calculating bitwise operations output.
AND truth table:
1 and 1 = 1
1 and 0 = 0
0 and 1 = 0
0 and 0 = 0

OR truth table:
1 or 1 = 1
1 or 0 = 1
0 or 1 = 1
0 or 0 = 0

XOR truth table:
1 xor 1 = 0
1 xor 0 = 1
0 xor 1 = 1
0 xor 0 = 0

We will represent numerical literals in binary notation for clarity in the following examples:
 */

fun main() {
   //For bitwise conjunction:
    val a = 0b10011 // 19
    val b = 0b11110 // 30
    println(a and b == 0b10010) // 18
    //outputs: true


    //For the disjunction:
//    val a = 0b101001 // 41
//    val b = 0b110011 // 51
//    println(a or b == 0b111011) // 59
    //outputs: true

   //For the exclusive disjunction, we will get 0 if the corresponding operand bits are both 0 or both 1:
//    val a = 0b110101 // 53
//    val b = 0b101010 // 42
//    println(a xor b == 31) // 11111
    //outputs: true

    //And the inversion is the simplest of all. All 0 become 1, and vice versa.
    //However, we have to remember that the Int type has 32 bits and Long has 64 bits.
    //That means, to get correct results for shorter binary numbers, we have to mask them with and():
//    println(0b101100.inv() and 0b111111 == 0b010011)


    //Binary Shifts
    //Binary shifts are exactly that: We take a binary number and shift it (move it) by the specified number of positions to the left or to the right.

    //Shift left:
//    println(0b110011 shl 2 == 0b11001100)
    //outputs true

    //Shift right:
//    println(0b1100110011 shr 2 == 0b11001100)
    //outputs true
    //the first 22 bits in this 32-bit binary are implicitly all zeroes

   /** So why do we need these operations anyway? Because they are really basic, and on most CPUs,
    they’ll take only one cycle to compute. The right shift represents integer division by 2-bits,
    while the left shift is multiplication by 2-bits. Let’s see them in action:
   */

    // rather than dividing 12/4 arithmetically, we can use bitwise operators by shifting 12 (in binary form) to the right 2 bits to output 3
//    println(12 shr 2 == 3) // 12 / 2^2 = 12 / 4 = 3

    // rather than multiplying 3*8 arithmetically, we can use bitwise operators by shifting 3 (in binary form) to left right 3 bits to output 24
//    println(3 shl 3 == 24) // 3 * 2^3 = 3 * 8 = 24

    // Unlike bitwise operators, actual multiplication and division might take more than one cycle.

   /**
    * A right shift (>> in C or shr in Kotlin) basically just moves the bits in the binary representation of a number to the right, effectively discarding the least significant bit(s), and adding zeros on the left.
    * For unsigned integers, this operation is equivalent to integer division by 2 to the power of the shift amount.
    *
    * A left shift (<< in C or shl in Kotlin) basically just moves the bits to the left, discarding the most significant bit(s), and adding zeros on the right.
    * This operation is equivalent to multiplying the number by 2 to the power of the shift amount.
    * */

    // Then, there’s the ability to pack information really tightly, each bit meaning something specific. Then we can use a mask and an and() operator to check for this specific property:
    val SKY_IS_BLUE_MASK = 0b00000000000001000000000000

    fun isSkyBlue(worldProperties: Int): Boolean =
        worldProperties and SKY_IS_BLUE_MASK != 0

    println(isSkyBlue(0b10011100111101011101010101))

    // Now, with or(), we can combine various flags together:
    val SKY_IS_BLUE = 0b00000000000001000000000000

    val SUN_IS_SHINING = 0b00000000000000100000000000

    val skyIsBlueAndSunShines = SKY_IS_BLUE or SUN_IS_SHINING // 0b00000000000001100000000000

   // Bitwise operations are useful in signal processing, high-performance calculations,
   // and when you want to create tightly-packed data structures.

}