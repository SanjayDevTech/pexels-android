package com.pexels.sample

class Event<T>(
    private val data: T?,
) {
    var eventHandled: Boolean = false
        private set

    val content: T?
        get() =
            if (eventHandled) null
            else {
                eventHandled = true
                data
            }
}