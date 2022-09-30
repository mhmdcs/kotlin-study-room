package InterfaceVsAbstract.InterfacesAsCallbacks

// the observed class (the "speaking" class that is being listened to)
class VideoPlayer {
    private lateinit var listener: VideoPlayerListener

    fun setListener(listener: VideoPlayerListener) {
        this.listener = listener
    }

    fun start() {
        println("Video started")
    }

    fun stop() {
        println("Video stopped")
    }

    fun pause() {
        println("Video paused")
    }

    fun release() {
        println("Resources released")
        listener.onResourcesReleased()
    }
}