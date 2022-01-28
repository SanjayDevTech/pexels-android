package com.pexels.android.model.task

fun interface OnSuccessListener<in T> {
    fun onSuccess(result: T)
}