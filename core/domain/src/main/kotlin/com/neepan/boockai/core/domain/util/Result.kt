package com.neepan.boockai.core.domain.util

/**
 * A generic, typed Result wrapper that works across all layers.
 * Prefer this over exceptions for expected failures.
 *
 * @param D the success data type
 * @param E the error type, must implement [Error]
 */
sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.neepan.boockai.core.domain.util.Error>(
        val error: E
    ) : Result<Nothing, E>
}

/**
 * Convenience alias for Result types that return Unit on success.
 */
typealias EmptyResult<E> = Result<Unit, E>

/**
 * Maps the success data of a Result to a new type.
 */
inline fun <T, E : Error, R> Result<T, E>.map(
    map: (T) -> R
): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Executes [action] if this is a Success, then returns the original Result.
 */
inline fun <T, E : Error> Result<T, E>.onSuccess(
    action: (T) -> Unit
): Result<T, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

/**
 * Executes [action] if this is an Error, then returns the original Result.
 */
inline fun <T, E : Error> Result<T, E>.onFailure(
    action: (E) -> Unit
): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}

/**
 * Converts a Result<T, E> to EmptyResult<E>, discarding the success value.
 */
fun <T, E : Error> Result<T, E>.asEmptyResult(): EmptyResult<E> {
    return map { }
}
