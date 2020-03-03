package entities

import kotlin.js.Date

data class Cope(
        val id: String,
        val url: String,
        val title: String,
        val createdAt: Date,
        val updateAt: Date,
        val content: List<CopeContent>,
        val icon: String? = null
)
