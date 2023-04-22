package kotlin101


fun main() {
    val byteArray = ByteArray(1024) // Creates a new array of size 1024 bytes (a kilobyte), with all 1024 elements initialized to zero.

    println(byteArray.toHexString())

    byteArray[0] = 127 // inserting a byte of 127 decimal to the 1st element in the array at index 0. Decimal value 127 is equal to Hexadecimal value 0x7F.
    byteArray[1] = 23 // inserting a byte of 23 decimal to the 2nd element in the array at index 1. Decimal value 23 is equal to Hexadecimal value 0x17.

    // a single signed byte (8 bits i.e. 8 slots of 0s and 1s) cannot hold an integer larger than 127 or less than -128, it's maximum number value is only from -128 to 127, for 256 numbers in total.
  //  byteArray[0] = 128 // compiler error, a signed byte cannot store a number more than 127
    byteArray[4] = 127 // inserting a byte of 127 decimal to the 5th element in the array at index 4. Decimal value 127 is equal to Hexadecimal value 0x7f.
    byteArray[3] = -128 // inserting a byte of -128 decimal to the 4th element in the array at index 3. Decimal value -128 is equal to Hexadecimal value 0x80.
  //  byteArray[0] = -129 // compiler error, a signed byte cannot store a number less than -128

    println(byteArray.toHexString())

    val trimmedByteArray = byteArray.copyOfRange(0, 5) // Returns a new array which is a copy of the specified range of the original array. From index 0 (inclusive) to index 5 (exclusive).
    println(trimmedByteArray.toHexString())
    println(trimmedByteArray.toList()) // printing the ByteArray as a List that contains elements of the array in decimal.


    // Creating a ByteArray from constant hexadecimals
    byteArrayOf(0xA1.toByte(), 0x2E.toByte(), 0x38.toByte(), 0xD4.toByte(), 0x89.toByte(), 0xC3.toByte())
    // better yet, we can use a function to achieve the same result in shorter, prettier code
    val byteArr = byteArrayOfInts(0xA1, 0x2E, 0x38, 0xD4, 0x89, 0xC3)


    // appending elements to a byte array:
    // ByteArray overloads the plus operator, so you can just add to the previous value directly with the + symbol:
    var bytes = byteArrayOf(0b1111, 0b0011, 0b1111111, 0xf, 18.toByte())
    bytes += 0b0001
    println(bytes.toList())

    // appending a new element to a byte array using plus() operator function directly:
    val newBytes = bytes.plus(0x7F).toList()
    println(newBytes)

    // converting decimal to binary:
    val binary = 255.toUInt().toString(radix = 2) // how to represent a decimal integer as binary
    println(binary)
    // how to represent a byte array's decimal elements as binaries
    bytes.toList().map {
        it.toString(radix = 2)
    }.let {
        println(it)
    }
}

// Function to create ByteArray from constant hexadecimals (pos means position)
fun byteArrayOfInts(vararg ints: Int) = ByteArray(ints.size) { pos -> ints[pos].toByte() }


// Kotlin's Array<Byte> is equivalent to java's Byte[] (the boxed byte). If you want primitive a byte array, use Kotlin's ByteArray type.
// ByteArray in Kotlin equals byte[] in Java. Whereas Array<Byte> in Kotlin equals Byte[] in Java
//There is a benefit to using ByteArray, as each entry is a primitive, so ByteArray requires less memory and potentially avoids auto-boxing.
//Due to Java's limitations, Kotlin has 9 array types: One of them is  Array<...> for arrays of references (in the JVM sense), and 8 other specialized array types, i.e. IntArray, ByteArray etc that can hold primitives.
//The main reason for this distinction is performance: if Kotlin didn't specialize arrays, it'd lead to a lot of accidental boxing/unboxing and also make arrays slow. This would be unacceptable because the only reason one might want to prefer arrays over collections is performance.
//Arrays due to fast execution consume more memory and have better performance. Collections, on the other hand, consume less memory but have low performance as compared to Arrays.
//Arrays are fixed in size i.e once the array with the specific size is declared then we can't alter its size afterward. The collection is dynamic in size i.e based on requirement size could be get altered even after its declaration.
//Arrays can hold the only the same type of data in its collection i.e only homogeneous data types elements are allowed in case of arrays. Collection, on the other hand, can hold both homogeneous and heterogeneous elements.
//Arrays can hold both object and primitive type data. On the other hand, collection can hold only object types but not the primitive type of data.


// In programming languages, the 0x notation is used to represent Hexadecimals (base 16) rather than any other base, for readability purposes and to also inform the compiler that it's dealing with a hex value
// 0x00 in Hexadecimal is 0 in Decimal, 0x01 in Hex is 1 in Dec, 0x08 in Hex is 8 in Dec, 0x0F in Hex is 15 in Dec, 0x10 in Hex is 16 in Dec, etc.

// extension functions to convert a Byte Array to a Hex String and vice versa

private val HEX_ARRAY = "0123456789abcdef".toCharArray()

fun ByteArray.toHexString(): String {
    val hex = CharArray(2 * this.size)
    this.forEachIndexed { i, byte ->
        val unsigned = 0xff and byte.toInt()
        hex[2 * i] = HEX_ARRAY[unsigned / 16]
        hex[2 * i + 1] = HEX_ARRAY[unsigned % 16]
    }

    return hex.joinToString("")
}

fun String?.hexStringToByteArray(): ByteArray {
    val hex = this.orEmpty()

    val len: Int = hex.length
    val byteArray = ByteArray(len / 2)

    var i = 0
    while (i < len) {
        // using left shift operator on every character
        byteArray[i / 2] =
            (
                    (Character.digit(hex[i], 16) shl 4).toByte() +
                            Character.digit(hex[i + 1], 16).toByte()
                    ).toByte()

        i += 2
    }

    return byteArray
}