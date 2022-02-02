package com.pexels.android.model.task

/**
 * Task object for non-coroutine callers
 */
interface PexelsTask<T> {
    fun setOnCompleteListener(cb: OnCompleteListener<T>): PexelsTask<T>

    fun setOnSuccessListener(cb: OnSuccessListener<T>): PexelsTask<T>

    fun setOnFailureListener(cb: OnFailureListener): PexelsTask<T>

    fun cancel()
}