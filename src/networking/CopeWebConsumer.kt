package networking

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import org.w3c.xhr.XMLHttpRequest


class CopeWebConsumer(private val baseUrl: String) : Consumer {

    private val provider = XMLHttpRequest()

private val token = "x"
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