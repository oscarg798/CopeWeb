package networking

interface Endpoint<T> {

    val path: String
    val method: Method

}