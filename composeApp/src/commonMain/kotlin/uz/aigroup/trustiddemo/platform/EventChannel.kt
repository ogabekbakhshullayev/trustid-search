package uz.aigroup.trustiddemo.platform

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object EventChannel {

    private val channel = Channel<Event>()

    fun sendEvent(event: Event) {
        channel.trySend(event)
    }

    fun receiveEvent(): Flow<Event> {
        return channel.receiveAsFlow()
    }
}

sealed interface Event {
    data object Unauthorized : Event
}