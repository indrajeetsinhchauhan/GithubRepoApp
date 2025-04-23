package com.indrajeetsinhchauhan.githubrepoapp.data.model

data class GitHubSearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<GitHubUser>
)
