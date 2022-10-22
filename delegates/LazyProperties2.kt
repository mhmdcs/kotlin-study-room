package delegates

// https://medium.com/tompee/idiomatic-kotlin-property-delegates-and-lazy-11207213a788


// implementing lazy initialization manually:
// To implement a lazy initialized property, you have to define a backing field that can be initialized anytime. (You can use either a lateinit var or a nullable type, it depends on your preference.)
// Then, manually override the property accessor method get() and add your initialization logic. Note that you have to do this to every property you want to lazily initialized. So this is a lot of boilerplate code.
// With Kotlin’s property delegation and lazy initialization built-in  language support, you can now say goodbye to this boilerplate code:
class Team {
    private var _hero: Hero? = null
    val manuallyLazyHero: Hero
        get() {
            if (_hero == null) {
                _hero = Hero("agility", "melee")
            }
            return _hero!!
        }
}

class Hero(val specialtyOne: String, val specialtyTwo: String)


fun main() {
    val team = Team() // despite us constructing an instance of ManuallyLazy class, its manuallyLazyHero property will never be initialized until we first access it in the following line.
    println(team.manuallyLazyHero.specialtyTwo)

    // The manual lazy code from above can now be replaced with a one-liner thanks to Kotlin's `by` keyword and standard library lazy() method. lazyHero property will never be initialized until we first access it in the following line.
    val lazyHero: Hero by lazy { Hero("agility", "melee") }
    println(lazyHero.specialtyOne)

    // Kotlin standard library already provides us with a lazy delegate.
    // The lazy() method accepts a lambda that serves as the property initializer.
    // Initialization is only performed on the first getValue() invocation.
    // The same value that was created will be returned on successive calls to getValue().
    // There are other benefits to using the lazy method other that boilerplate code reduction.
    // For instance, thread-synchronization is guaranteed as well, and can be configured to your liking.
}