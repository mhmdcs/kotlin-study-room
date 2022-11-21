package coroutines.coroutine_builders

/**
 * If no dispatcher is specified in the coroutine builder, you get the dispatcher from whichever CoroutineScope you're starting your coroutine in. If there is no dispatcher in that scope's context, the coroutine will use Dispatchers.Default (see the doc of launch for instance).

Note that the scope is the receiver of the coroutine builder call:

- if you see GlobalScope.launch { ... } then GlobalScope is the scope
- if you see scope.launch { ... }, look at that scope
- if you see launch { .. } in the wild, some instance of CoroutineScope must be available as this in that piece of code, so that's the parent scope (see below for an example on where it could be coming from)

Here is some info about the dispatchers used in the most common coroutine scopes:
- If the scope is GlobalScope, then it doesn't have any dispatcher, so as mentioned before the coroutines will use Dispatchers.Default.
-  If the scope is lifecycleScope or viewModelScope provided by Android, then coroutines will use Dispatchers.Main.immediate by default.
- If the scope is created with the CoroutineScope() factory function without particular dispatcher, Dispatchers.Default will be used (see the "Custom usage" section in the documentation).
- If the scope is created using MainScope() and without particular dispatcher, it will use Dispatchers.Main as per the same documentation.
- If the scope is provided by runBlocking, then it will use a special dispatcher that works like an event loop and executes your coroutines in the thread that called runBlocking

If we assume you don't specify any dispatcher anywhere, there is a good chance your coroutines are running on Dispatchers.Main (in Android) or Dispatchers.Default.

Dispatchers.Main would be really bad for IO stuff, because it would freeze your UI while the IO is happening. I believe Android probably crashes in case you run the wrong things in this dispatcher, but I haven't done Android dev in a while, so I can't say for sure.

Dispatchers.Default is a shared thread pool that's sized based on the number of cores of the machine executing the code, so it's suitable for CPU-bound tasks. If you launch a bunch of coroutines that perform blocking IO in this dispatcher, you could block all your threads and prevent other coroutines from running, which is really not ideal - it could cause lags or slowness, especially if you rely a lot on coroutines.

Dispatchers.IO is not magic, but it will spawn more threads as necessary if too many threads are blocked, so that other coroutines can run. You will still incur the additional memory of extra threads, but other coroutines will be free to run while some threads are blocked on IO.

More:
https://stackoverflow.com/a/69853768/9133569
https://stackoverflow.com/a/70208525/9133569
 * **/