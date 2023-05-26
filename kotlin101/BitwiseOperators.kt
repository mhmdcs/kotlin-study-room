package kotlin101

/**
Kotlin bitwise operators vs logical operators

Logical operations assume that the entire variable represents either true or false, combining two integer values using a logical operator produces one result, with the variable representing true or false.

Bitwise operations assume that each bit in the variable's value represents a separate true or false, combining two integer values using a bitwise operator produces 8 (or 16 or 32 or 64) separate bits each representing a true or a false.

Logical operators: Compare bits of the given object and always return a Boolean result. Logical operators in Kotlin are && (logical and) || (logical or) and ! (logical not):
&& (logical and) returns true if both statements are true.
|| (logical or)  returns true if one of the statements is true.
! (logical not) reverse the result, returns false if the result is true.

Bitwise operators perform operations on individual bits, and the result is also always a bit.
And yes, we can do bitwise operations in Kotlin, too.
Despite the fact that the usual symbols, like <<, >>, |, &, and ^, are missing, we can do everything we can in C (or Java), but with sensibly named functions of classes Int and Long, that make use of Kotlin's `infix` keyword to improve bitwise operations readability.

The Kotlin community has been arguing about bitwise operators for ten years. Those who support the idea say that it’s a requirement of their low-level domains – usually, sound or video processing.
Those who oppose it put other things as more important and also point out that sometimes the code overcrowded with bitwise symbols is completely unreadable in languages that allow them.
https://youtrack.jetbrains.com/issue/KT-1440

Kotlin Bitwise Operations and Their Java Counterparts:
Operation Name __ Java Operator __ Kotlin Int/Long Function
Conjunction (and) __ a & b __ a and b
Disjunction (or) __ a | b __ a or b
Exclusive disjunction (xor) __ a ^ b __ a xor b
Inversion (not) __  ~ a __ a.inv()
Shift Left __ a << bits __ a shl bits
Shift Right __ a >> bits __ a shr bits
Unsigned Shift Right (Shift Right Fill Zero) __ a >>> bits __ a ushr bits

Java also includes assignment operators modified with each of the bitwise operators, like |=. In Kotlin, we will have to repeat ourselves: a = a or b.

The word “and” is conjunctive, meaning it combines things. Conversely, the word “or” is disjunctive, meaning it separates things.

Bitwise operations in Kotlin are represented by functions that can be called in infix form. They can be applied only to Int and Long. Bitwise and bit shift operators are used on only two integral types (Int and Long) to perform bit-level operations.

Bitwise conjunction, disjunction, and inversion work similarly to their logical counterparts, but they affect each bit of their operands separately.
Remember the truth table when calculating the output.
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

We will represent numerical literals in the binary notation for clarity in the following examples:
 */

fun main() {
    // For bitwise conjunction:
    val a = 0b10011 // 19
    val b = 0b11110 // 30
    println(a and b == 0b10010) // 18

// For the disjunction:
//    val a = 0b101001 // 41
//    val b = 0b110011 // 51
//    println(a or b == 0b111011) // 59

// For the exclusive disjunction, we will get 0 if the corresponding operand bits are both 0 or both 1:
//    val a = 0b110101 // 53
//    val b = 0b101010 // 42
//    println(a xor b == 31) // 11111

//And the inversion is the simplest of all. All 0 become 1, and vice versa.
//However, we have to remember that the Int type has 32 bits and Long has 64.
//That means, to get correct results for shorter binary numbers, we have to mask them with and:
  //  println(0b101100.inv() and 0b111111 == 0b010011)
}