package com.indrajeetsinhchauhan.githubrepoapp.data.model

data class Repository(
    val name: String,
    val language: String?,
    val stargazers_count: Int,
    val description: String?,
    val fork: Boolean,
    val html_url: String
)
