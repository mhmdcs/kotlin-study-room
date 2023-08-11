package coroutines.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

// In Kotlin Coroutines, the debounce operator is a function provided by the Flow API that you can use to handle rapid changes to some type of data. It's often used to filter out rapid, successive events.
// The debounce function only emits an item from an upstream Flow if a certain timespan has passed without it emitting another item.
// It waits for the specified delay time after each emitted item and only allows the item to be emitted if no other item is emitted in that time.
// Here's a simple example in the context of a search bar where user's input is being tracked:
fun main() {
    val myScope = CoroutineScope(Dispatchers.Default)
    val searchQuery = MutableStateFlow("") // A MutableStateFlow to hold the current query
    // Observe the searchQuery Flow
    searchQuery
        .debounce(300) // Wait for 300ms of no new emissions
        .filter { it.isNotEmpty() } // Don't do anything for an empty query
        .distinctUntilChanged() // Only proceed if the current query is different from the last
        .onEach { query ->
            // Perform search operation
        }
        .launchIn(myScope) // Launch the flow in myScope's Coroutine scope
}
// In this example, the debounce operator ensures that the search operation is not performed too frequently, thus preventing unnecessary load on the system and improving overall performance.
//Remember to ensure that the coroutine scope used to collect the flow is active. In the above example, the flow is launched in the myScope. If you're using a custom scope, make sure it's active when the flow is collected.

/**
The term "debouncing" originated from electrical engineering.
In the context of electrical switches (like buttons or keys), bouncing is the tendency of any two metal contacts to generate multiple signals as the contacts close or open; there's usually a bit of a bounce, a kind of vibration or multiple contacts before the switch settles into its new state.

Debouncing, then, is the process of taking the noisy signals produced by mechanical contact bounce (the rapid on-off-on-... sequence) and smoothing them out into a single clean "on" signal (or "off", as the case may be).
This can be done in hardware (with resistors, capacitors, Schmitt triggers) or in software (with various algorithms).

In the context of software development, we use the term "debouncing" to refer to similar strategies.
It means we are "smoothing out" rapid, successive triggers of the same event (like key presses, mouse clicks, or even rapid updates to a data stream), and ensuring that we only respond once to each meaningful group of triggers.
 * */