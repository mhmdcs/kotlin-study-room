package coroutines.coroutine_builders

/**
 * CoroutineScope (uppercase C) and coroutineScope (lowercase c) are completely two different things.
 *
 *
 * coroutineScope does not define its own dispatcher, so you inherit the dispatcher from coroutineScope's caller, keep this in mind
 * because if you call coroutineScope {} inside runBlocking {} or lifecycleScope {} and viewModelScope {}, then it uses uses the single thread it is called on, which is the main thread.
 */