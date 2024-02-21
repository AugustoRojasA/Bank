package com.server.bancamovil.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
sealed class Resource<out T>(val status: Status, var data: @UnsafeVariance T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(Status.SUCCESS, data)
    class Loading<T>(data: T? = null) : Resource<T>(Status.LOADING)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(Status.ERROR, data, message)
}