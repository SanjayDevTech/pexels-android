package com.pexels.android.model.task

/**
 * Task object for non-coroutine callers
 */
interface PexelsTask<T> {
    fun setOnCompleteListener(cb: OnCompleteListener<T>)

    fun setOnSuccessListener(cb: OnSuccessListener<T>)

    fun setOnFailureListener(cb: OnFailureListener)

    fun cancel()
}