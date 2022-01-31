package com.pexels.android

import com.google.common.truth.Truth.assertThat

class ExceptionSubject(
    private val throwable: Throwable?
) {
    fun <T : Throwable> isException(exception: Class<T>) {
        assertThat(throwable).isInstanceOf(exception)
    }

    fun isNoException() {
        assertThat(throwable).isNull()
    }
}

suspend fun assertExceptionSuspend(code: suspend () -> Unit): ExceptionSubject {
    var exception: Throwable? = null
    try {
        code()
    } catch (e: Throwable) {
        exception = e
    }
    return ExceptionSubject(exception)
}