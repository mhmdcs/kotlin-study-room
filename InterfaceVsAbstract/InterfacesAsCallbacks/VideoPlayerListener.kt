package InterfaceVsAbstract.InterfacesAsCallbacks

// fun keyword denotes that an interface is a SAM (Single Abstract Method) functional interface that can be implemented/instantiated with just a lambda
fun interface VideoPlayerListener {
    // this function will be implemented as a callback that will be triggered as soon an event calls it
    fun onResourcesReleased()
}