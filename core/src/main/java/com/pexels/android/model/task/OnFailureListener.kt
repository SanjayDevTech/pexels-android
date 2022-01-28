package com.pexels.android.model.task

fun interface OnFailureListener {
    fun onFailure(exception: Exception)
}