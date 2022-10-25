package designpatterns

// another example of using proxy pattern that controls access to the real object by implementing caching mechanism that checks if a call has already been made before calling it again

class Video

interface VideoDownloader {
    fun getVideo(videoTitle: String): Video?
}

class RealVideoDownloader: VideoDownloader {
    override fun getVideo(videoTitle: String): Video? {
        println("Connecting to https://youtube.com")
        println("Fetching $videoTitle video metadata")
        println("Downloading $videoTitle video file")
        return Video()
    }
}

class ProxyVideoDownloader: VideoDownloader {
    private val videoCache = mutableMapOf<String, Video?>()
    private val realVideoDownloader = RealVideoDownloader()
    override fun getVideo(videoTitle: String): Video? {
        if(!videoCache.containsKey(videoTitle)) { // controlling access to the real object is what makes Proxy pattern different from Decorator pattern
            videoCache[videoTitle] = realVideoDownloader.getVideo(videoTitle)
        }
        return videoCache[videoTitle]
    }
}

fun main() {
    // if we spam the same network request using the real object, it'll be recalled again each time despite returning the same result, which is expensive
//    val realVideoDownloader: VideoDownloader = RealVideoDownloader()
//    realVideoDownloader.getVideo("cute cats")
//    realVideoDownloader.getVideo("cute cats")
//    realVideoDownloader.getVideo("Kotlin tutorials")
//    realVideoDownloader.getVideo("cute cats")
//    realVideoDownloader.getVideo("Kotlin tutorials")

    // instead we should use our proxy object since it has modified the original object's behavior with caching mechanism to optimize memory usage.
    val proxyVideoDownloader: VideoDownloader = ProxyVideoDownloader()
    proxyVideoDownloader.getVideo("cute cats")
    proxyVideoDownloader.getVideo("cute cats")
    proxyVideoDownloader.getVideo("Kotlin tutorials")
    proxyVideoDownloader.getVideo("cute cats")
    proxyVideoDownloader.getVideo("Kotlin tutorials")
}