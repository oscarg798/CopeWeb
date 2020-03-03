package repositories

import entities.ApiCope
import entities.ApiCopeContent
import entities.Cope
import entities.CopeContent
import networking.Consumer
import networking.Endpoint

class CopeRepositoryImpl(private val consumer: Consumer,
                         private val getCopesEndpoint: Endpoint<Array<ApiCope>>) : CopeRepository {

    override suspend fun getCopes(): Array<Cope> {
        val result = consumer.consume(getCopesEndpoint)
        val copes = arrayOf<Cope>()

        for ((i, apiCope) in result.withIndex()) {
            copes[i] = Cope(apiCope.id, apiCope.url, apiCope.title, apiCope.createdAt, apiCope.updateAt, getApiCopeContent(apiCope.content), apiCope.icon)

        }

        return copes
    }

    private fun getApiCopeContent(apiContents: List<ApiCopeContent>): List<CopeContent> {
        val contents = ArrayList<CopeContent>()
        for (i in 0 until apiContents.size) {
            val apiContent = apiContents[i]
            contents.add(CopeContent(apiContent.id, apiContent.text, apiContent.createdAt, apiContent.updatedAt))
        }

        return contents
    }
}