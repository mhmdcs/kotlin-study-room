package delegates

// The basic idea of class delegation is that, you can implement an interface by owning an object that implements the same interface,
// and thus you delegate the interface implementation responsibility to that object.
// Class Delegation is an alternative to inheritance for reusing code among multiple classes.

// When delegating classes, it is mandatory that the delegate and the class that performs delegation both inherit the same interface,
// you then use the magic Kotlin keyword is 'by' to delegate the responsibility of initializing the property to that class.
// Essentially under the hood, composition is used to achieve the delegate pattern.
// https://medium.com/tompee/idiomatic-kotlin-class-delegates-288b24c37ac8

interface AttackType {
    fun getAttackType(): String
}

class Ranged : AttackType {
    override fun getAttackType(): String = "Ranged"
}

interface HeroType {
    fun getAttributeType(): String
}

class Strength : HeroType {
    override fun getAttributeType(): String = "Strength"
}

class Warrior : AttackType by Ranged(), HeroType by Strength()

fun main() {
    val warrior = Warrior()
    val attackType = warrior.getAttackType()
    val attributeType = warrior.getAttributeType()
    println("$attackType\n$attributeType")
}