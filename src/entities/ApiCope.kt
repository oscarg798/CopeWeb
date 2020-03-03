package entities

import kotlin.js.Date

data class ApiCope(val id: String,
                   val url: String,
                   val title: String,
                   val createdAt: Date,
                   val updateAt: Date,
                   val content: List<ApiCopeContent>,
                   val icon: String? = null)

