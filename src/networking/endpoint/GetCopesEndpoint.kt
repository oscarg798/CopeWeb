package networking.endpoint

import entities.ApiCope
import networking.Endpoint
import networking.Method

object GetCopesEndpoint : Endpoint<Array<ApiCope>> {
    override val path: String
        get() = "cope"
    override val method: Method = Method.GET

}