package com.pexels.android.model.task

fun interface OnCompleteListener<in T> {
    fun onComplete(
        result: T?,
        exception: Exception?,
    )
}