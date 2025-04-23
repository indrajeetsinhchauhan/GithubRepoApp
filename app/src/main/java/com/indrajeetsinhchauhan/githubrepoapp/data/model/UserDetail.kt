package com.indrajeetsinhchauhan.githubrepoapp.data.model

data class UserDetail(
    val login: String,
    val avatar_url: String,
    val name: String?,
    val followers: Int,
    val following: Int
)
