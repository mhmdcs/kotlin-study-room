package tlv

/**
TLV (Tag, Length, Value) is a data representation format often used in various communication protocols and data storage. It's a way to structure data for efficient encoding, decoding, and data parsing. Here's a breakdown:

1. Tag: A unique identifier for the data item. It tells you what kind of data is being presented.
2. Length: Specifies the length of the data item (the value). It tells you how long the data item is, usually in bytes.
3. Value: The actual data corresponding to the tag.

This structure makes it easy to parse messages or data because once you read the tag, you know what type of information is coming. By reading the length, you know how much data to read for the value. After processing the value, you can move to the next tag, and so on.

TLV is commonly used in technologies like EMV chip cards, ASN.1 encoding, some network protocols, and more.
 * */


// Let's dive into TLV using Kotlin with a basic example.
// 1. TLV Representation:
// A basic TLV might look something like this:
// Tag (1 byte) | Length (1 byte) | Value (variable length as indicated by Length)

// For our example, let's assume:
// - Tag: `0x01` represents a text string.
// - Tag: `0x02` represents an integer value.

// 2. Kotlin Data Structure:
data class TLV(val tag: Byte, val length: Byte, val value: ByteArray)

// 3. Parsing TLV:
// Given a stream of bytes, you can parse it into a TLV object.
fun parseTLV(input: ByteArray): TLV {
    val tag = input[0]
    val length = input[1]
    val value = input.sliceArray(2 until 2 + length)
    return TLV(tag, length, value)
}

// 4. Encoding TLV:
// You can also encode a TLV object back into a stream of bytes.
fun encodeTLV(tlv: TLV): ByteArray {
    return byteArrayOf(tlv.tag, tlv.length) + tlv.value
}

// 5. Reading and Understanding TLV:
// Depending on the tag, you can interpret the value.
fun interpretTLV(tlv: TLV): String {
    return when (tlv.tag) {
        0x01.toByte() -> "Text: ${String(tlv.value)}"
        0x02.toByte() -> "Integer: ${tlv.value.toInt()}"
        else -> "Unknown tag"
    }
}

// 6. Manipulating TLV:
// You can easily change the value of a TLV or extend it to handle other tags.
fun changeValue(tlv: TLV, newValue: ByteArray): TLV {
    return tlv.copy(value = newValue, length = newValue.size.toByte())
}

// 7. Usage Example:
fun main() {
    // Example TLV bytes: Tag = 0x01, Length = 0x05, Value = "Hello"
    val tlvBytes = byteArrayOf(0x01, 0x05) + "Hello".toByteArray()
    val tlvObject = parseTLV(tlvBytes)

    println(interpretTLV(tlvObject))  // Prints: Text: Hello

    val changedTLV = changeValue(tlvObject, "World".toByteArray())
    println(interpretTLV(changedTLV))  // Prints: Text: World

    val tlv = TLV(0x02, 0x04, 1997.toByteArray()) // Length Specification: In TLV encoding, the length byte specifies how many bytes the value takes. If you have an integer that's 1997, it cannot be represented in a single byte (0x01) because its value (1997) exceeds the maximum value a byte can hold (255). You need to match the length with the actual byte length of the integer. Since an Int in Kotlin is 4 bytes (32 bits), you should set the length to 4 bytes (0x04).
    val intValue = 1997
    val intByteArray = intValue.toByteArray() // This will be 2 bytes for 1997
    val length = intByteArray.size.toByte() // instead of manually passing 0x04 for the length byte, we instead pass size of the intByteArray value and convert it to a byte, this is safer :)
    val tlv2 = TLV(0x02, length, intByteArray)
    println(interpretTLV(tlv))  // Prints: Integer: 1997
}
// This is a simple representation of TLV. In real-world applications, TLV structures might be nested, might have tags or lengths that span multiple bytes, or might have other complexities.
// But this example should give you a solid foundation in understanding and manipulating TLV using Kotlin.

// helper extension functions to convert integer to bye array and vice versa :

// this toByteArray() method correctly converts a 32-bit integer to a byte array.
// However, in a TLV structure, you may not always want to use all 4 bytes for the representation of an integer if it can be represented in fewer bytes. For example, the integer 1997 can be represented in 2 bytes, so I'll also write another extension function below that's more optimized than this.
private fun Int.toByteArray(): ByteArray {
    return byteArrayOf(
        (this and 0xFF).toByte(),
        ((this shr 8) and 0xFF).toByte(),
        ((this shr 16) and 0xFF).toByte(),
        ((this shr 24) and 0xFF).toByte()
    )
}

// This method is more efficient than the one above, it creates a byte array with the minimum number of bytes needed to represent the integer, but we'll comment it since the above is simpler to read.
// This method creates a byte array with the minimum number of bytes needed to represent the integer.
//private fun Int.toByteArray(): ByteArray {
//    val list = mutableListOf<Byte>()
//    var temp = this
//    do {
//        list.add(0, (temp and 0xFF).toByte())
//        temp = temp shr 8
//    } while (temp != 0)
//    return list.toByteArray()
//}

// This method assumes the byte array is in little-endian format.
private fun ByteArray.toInt(): Int {
    var result = 0
    this.forEachIndexed { index, byte ->
        result = result or (byte.toInt() and 0xFF shl 8 * index)
    }
    return result
}


private fun Long.toByteArray(): ByteArray {
    return byteArrayOf(
        (this and 0xFF).toByte(),
        ((this shr 8) and 0xFF).toByte(),
        ((this shr 16) and 0xFF).toByte(),
        ((this shr 24) and 0xFF).toByte(),
        ((this shr 32) and 0xFF).toByte(),
        ((this shr 40) and 0xFF).toByte(),
        ((this shr 48) and 0xFF).toByte(),
        ((this shr 56) and 0xFF).toByte()
    )
}

private fun ByteArray.toLong(): Long {
    var result = 0L
    for (i in this.indices) {
        result = result or ((this[i].toLong() and 0xFF) shl (8 * i))
    }
    return result
}

private fun Double.toByteArray(): ByteArray {
    return this.toRawBits().toByteArray()
}

private fun ByteArray.toDouble(): Double {
    return Double.fromBits(this.toLong())
}