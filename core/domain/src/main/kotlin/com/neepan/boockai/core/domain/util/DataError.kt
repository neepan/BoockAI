package com.neepan.boockai.core.domain.util

/**
 * Shared error types for data layer operations.
 * Covers local persistence errors.
 */
sealed interface DataError : Error {

    enum class Local : DataError {
        DISK_FULL,
        NOT_FOUND,
        UNKNOWN
    }
}
