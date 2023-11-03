package tlv

// Let's look at a more complex TLV representation and walk through it step by step.
// Complex TLV Format:

// 1. Tag: Can be 1 to 2 bytes.
// - If the first byte's highest bit is `0`, it's a single-byte tag.
// - If the first byte's highest bit is `1`, it's a two-byte tag.

// 2. Length: Can be 1 to 2 bytes.
// - If the first byte's highest bit is `0`, it's a single-byte length.
// - If the first byte's highest bit is `1`, the length is stored in the next byte.

// 3. Value: Based on the length. It can also contain nested TLV structures.

// 1. Kotlin Data Structure:
data class TagLengthValue(val tag: Int, val length: Int, val value: ByteArray)

// 2. Parsing Functions:
fun parseTag(input: ByteArray, offset: Int): Pair<Int, Int> {
    return if (input[offset].toInt() and 0x80 == 0) {
        Pair(input[offset].toInt(), 1)
    } else {
        Pair((input[offset].toInt() and 0x7F shl 8) or (input[offset + 1].toInt() and 0xFF), 2)
    }
}

fun parseLength(input: ByteArray, offset: Int): Pair<Int, Int> {
    return if (input[offset].toInt() and 0x80 == 0) {
        Pair(input[offset].toInt(), 1)
    } else {
        Pair(input[offset + 1].toInt() and 0xFF, 2)
    }
}

fun parseTLV(input: ByteArray, offset: Int = 0): TagLengthValue {
    val (tag, tagSize) = parseTag(input, offset)
    val (length, lengthSize) = parseLength(input, offset + tagSize)
    val totalLength = offset + tagSize + lengthSize + length
    val value = input.sliceArray(offset + tagSize + lengthSize until totalLength)
    return TagLengthValue(tag, length, value)
}

// Explanation:
// - `parseTag`: Reads the tag from the input, starting at `offset`. If the tag's highest bit is `0`, it returns the tag value as a single byte. Otherwise, it combines two bytes to form the tag.
// - `parseLength`: Reads the length from the input. If the highest bit is `0`, it returns the length as a single byte. Otherwise, it reads the next byte as the length.
// - `parseTLV`: Uses `parseTag` and `parseLength` to decode a TLV object.

// 3. Encoding Functions:

fun encodeTag(tag: Int): ByteArray {
    return if (tag and 0xFF00 == 0) {
        byteArrayOf(tag.toByte())
    } else {
        byteArrayOf((tag shr 8 or 0x80).toByte(), (tag and 0xFF).toByte())
    }
}

fun encodeLength(length: Int): ByteArray {
    return if (length and 0xFF00 == 0) {
        byteArrayOf(length.toByte())
    } else {
        byteArrayOf((0x80).toByte(), length.toByte())
    }
}

fun encodeTLV(tlv: TagLengthValue): ByteArray {
    return encodeTag(tlv.tag) + encodeLength(tlv.length) + tlv.value
}

// Explanation:
// - `encodeTag`: Converts the tag to 1 or 2 bytes.
// - `encodeLength`: Converts the length to 1 or 2 bytes.
// - `encodeTLV`: Uses `encodeTag` and `encodeLength` to create a TLV byte array.

// 4. Nested TLV Structures:
// Nested structures mean that a TLV's value itself can be another TLV. To handle this, we can create a recursive parsing function.
fun parseNestedTLVs(input: ByteArray): List<TagLengthValue> {
    val tlvs = mutableListOf<TagLengthValue>()
    var offset = 0

    while (offset < input.size) {
        val tlv = parseTLV(input, offset)
        tlvs.add(tlv)
        offset += 2 + tlv.length + (if (tlv.tag and 0x80 == 0) 0 else 1) + (if (tlv.length and 0x80 == 0) 0 else 1)
    }

    return tlvs
}

// 5. Example:
// Let's assume a TLV structure representing user data:
// - Tag `0x0101` indicates a username (string).
// - Tag `0x0102` indicates an age (integer).
// - Tag `0x02` indicates a user profile (nested TLV with username and age).
fun main() {
    // Nested TLV: UserProfile (Tag 0x02) containing UserName (Tag 0x0101) as "Alice" and Age (Tag 0x0102) as 28
    val tlvBytes = byteArrayOf(0x02, 0x09, 0x01, 0x01, 0x05) + "Alice".toByteArray() + byteArrayOf(0x01, 0x02, 0x02, 0x1C)
//    val tlvBytes = byteArrayOf(0x02, 0x0C, 0x01, 0x01, 0x05) + "Alice".toByteArray() + byteArrayOf(0x01, 0x02, 0x01, 0x1C)

    val tlvs = parseNestedTLVs(tlvBytes)

    tlvs.forEach { tlv ->
        when (tlv.tag) {
            0x02 -> {
                println("User Profile:")
                parseNestedTLVs(tlv.value).forEach { nestedTlv ->
                    when (nestedTlv.tag) {
                        0x0101 -> println("  Username: ${String(nestedTlv.value)}")
                        0x0102 -> println("  Age: ${nestedTlv.value[1].toInt()}")
                    }
                }
            }
        }
    }
}

// This is a comprehensive TLV example in Kotlin, showing how you might parse and interpret a complex byte array into meaningful data.
// Adjustments can be made to handle even more complexities, but this example covers the majority of them.


/**
Let's analyze the TLV bytes you are using as input:

val tlvBytes = byteArrayOf(0x02, 0x09, 0x01, 0x01, 0x05) + "Alice".toByteArray() + byteArrayOf(0x01, 0x02, 0x02, 0x1C)

This sequence should represent:

1. A TLV structure with Tag `0x02` and Length `0x09`.
2. Inside this TLV structure, we expect two nested TLV elements:
- A TLV with Tag `0x0101`, Length `0x05`, and Value `"Alice"`.
- A TLV with Tag `0x0102`, Length `0x02`, and a Value that should represent the integer 28 (0x1C in hexadecimal).

Breaking down the byte array:

- `0x02` - Start of the outer TLV (User Profile)
- `0x09` - Length of the outer TLV value
- `0x01, 0x01` - Start of the first nested TLV (Username)
- `0x05` - Length of the "Alice" string
- `"Alice".toByteArray()` - Value for the first nested TLV (Username)
- `0x01, 0x02` - Start of the second nested TLV (Age)
- `0x02` - Length of the age value, which should be 1 byte instead of 2
- `0x1C` - Value for the second nested TLV (Age)

However, the length of the outer TLV (`0x09`) seems incorrect. It should include the full length of the nested TLVs, including their tags and lengths. For the given example:

- The first nested TLV has 2 bytes for the tag, 1 byte for the length, and 5 bytes for the value `"Alice"`, totaling 8 bytes.
- The second nested TLV should have 2 bytes for the tag, 1 byte for the length, and 1 byte for the value `0x1C`, totaling 4 bytes.
- Thus, the outer TLV's length should be 8 + 4 = 12 bytes (`0x0C` in hexadecimal), not 9.

Let's correct the length of the outer TLV and the length of the age to reflect a single byte (since the age is a single byte value):

val tlvBytes = byteArrayOf(0x02, 0x0C, 0x01, 0x01, 0x05) + "Alice".toByteArray() + byteArrayOf(0x01, 0x02, 0x01, 0x1C)

Now the outer TLV correctly reflects the combined length of its nested TLVs, and it should parse without error. The corrected TLV structure now tells the parser that it should expect 12 bytes of nested TLV data, which matches what we've provided.

Try running your parsing function with this corrected TLV byte array.
 * */