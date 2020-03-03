package networking

interface Consumer {

    suspend fun <T> consume(endpoint: Endpoint<T>): T
}