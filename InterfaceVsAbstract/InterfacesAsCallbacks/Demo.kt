package InterfaceVsAbstract.InterfacesAsCallbacks

// functional interfaces, can be used to create callbacks/listeners

fun main() {
    // method 1: instantiating the VideoPlayerListener interface by constructing an object of an anonymous class that implements the interface
    // we could implement/instantiate the interface and override its method with just a lambda thanks to the "fun" modifier on the interface, which told the compiler to mark the interface as a SAM functional interface
    val videoPlayer = VideoPlayer()
    videoPlayer.setListener(object : VideoPlayerListener {
        override fun onResourcesReleased() { // this callback function will be fired as soon as an event calls it
            println("onResourcesReleased() got called back")
        }
    })
    videoPlayer.release()

    // method 2
  //  Demo()
}

// method 2: instantiating the VideoPlayerListener interface by constructing an object of a named class that implements the interface
// the observer class (the listener class)
class Demo : VideoPlayerListener {
    init {
        val videoPlayer = VideoPlayer()
        videoPlayer.setListener(this)
        videoPlayer.release()
    }

    override fun onResourcesReleased() {
        println("onResourcesReleased() got called back")
    }
}