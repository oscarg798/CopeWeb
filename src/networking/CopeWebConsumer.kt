package networking

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import org.w3c.xhr.XMLHttpRequest


class CopeWebConsumer(private val baseUrl: String) : Consumer {

    private val provider = XMLHttpRequest()

    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJjb3BlcyI6WyI1ZTBmYTgwN2UwYzAxZTE3YmFiYTM0OWEiLCI1ZTQ2N2I0Y2Q1NTZjZTY3MzI3MGMxYTQiLCI1ZTQ2N2I0Y2Q1NTZjZTY3MzI3MGMxYTQiLCI1ZTQ2N2I0Y2Q1NTZjZTY3MzI3MGMxYTQiLCI1ZTQ2N2I5OGQ1NTZjZTY3MzI3MGMxYTkiLCI1ZTQ2YzRmYWQ1NTZjZTY3MzI3MGMxYWMiLCI1ZTQ2Yzc5OWQ1NTZjZTY3MzI3MGMxYWYiLCI1ZTQ2Yzc5OWQ1NTZjZTY3MzI3MGMxYWYiLCI1ZTQ2Yzc5OWQ1NTZjZTY3MzI3MGMxYWYiLCI1ZTQ2Y2M0NGQ1NTZjZTY3MzI3MGMxYjQiLCI1ZTQ3MmI1Y2Q1NTZjZTY3MzI3MGMxYjciLCI1ZTQ3MmU4NGQ1NTZjZTY3MzI3MGMxYmEiLCI1ZTQ3MmZiN2Q1NTZjZTY3MzI3MGMxYmQiLCI1ZTQ3MzBjMmQ1NTZjZTY3MzI3MGMxYzAiLCI1ZTQ4MTljNmQ1NTZjZTY3MzI3MGMxYzMiLCI1ZTQ4MjYyZmQ1NTZjZTY3MzI3MGMxYzYiLCI1ZTRiZWY3ZmQ1NTZjZTY3MzI3MGMxYzkiLCI1ZTRiZmE4N2VkNjJmZTUzNjIwZjJjZDQiLCI1ZTRmYWIzYmVkNjJmZTUzNjIwZjJjZDciLCI1ZTRmYWIzYmVkNjJmZTUzNjIwZjJjZDciLCI1ZTRmZDU3N2VkNjJmZTUzNjIwZjJjZGIiLCI1ZTRmZDcxOWVkNjJmZTUzNjIwZjJjZGUiLCI1ZTU5N2E2ODI3MDJmMTY1MjhjNzNjZWUiLCI1ZTU5N2E2ODI3MDJmMTY1MjhjNzNjZWUiLCI1ZTU5N2E5YTI3MDJmMTY1MjhjNzNjZjIiLCI1ZTVkNjM1YzI3MDJmMTY1MjhjNzNjZjUiLCI1ZTVkOTYzYzhkMzRiMjU4MDQ4ZjhhOTciXSwiX2lkIjoiNWUwZjk1NjhiYjY3NWUxMzk2YWZjODg1IiwibmFtZSI6Ik9zY2FyIEdhbGxvbiIsImVtYWlsIjoib3NjYXJnNzk4QGdtYWlsLmNvbSIsIl9fdiI6Mjd9.JjWQMx1ofdCoMFmn3COpysxqke9oGmGU7e5INF4FVEk"

    override suspend fun <T> consume(endpoint: Endpoint<T>): T {
        return consumeAsync(endpoint).await()
    }

    private fun <T> consumeAsync(endpoint: Endpoint<T>): Deferred<T> {
        val deferred = CompletableDeferred<T>()

        runCatching {
            provider.open(endpoint.method.name, baseUrl + endpoint.path)
            provider.setRequestHeader("Authorization", "bearer $token")
            provider.onload = {
                deferred.complete((JSON.parse(provider.responseText) as T))
            }

            provider.send()
        }.onFailure {
            deferred.completeExceptionally(it)
        }

        return deferred
    }

}