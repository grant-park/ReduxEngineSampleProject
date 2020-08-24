package ai.grant.reduxenginesampleproject.extensions

import kotlinx.coroutines.channels.ReceiveChannel

fun <T> MutableList<ReceiveChannel<T>>.dispose() {
    forEach { it.cancel() }
}