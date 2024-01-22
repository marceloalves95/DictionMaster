package br.com.dictionmaster.core.extensions

import br.com.dictionmaster.network.event.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

suspend fun <T> executeFlow(
    getRepository:suspend (() -> T)
): Flow<Event<T>> = flow {
    emit(Event.Loading)
    val data = getRepository()
    emit(Event.Data(data))
}.catch {
    val exception = Throwable(it.cause)
    emit(Event.Error(exception))
}