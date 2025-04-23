package com.indrajeetsinhchauhan.githubrepoapp.data.api

import com.indrajeetsinhchauhan.githubrepoapp.data.model.GitHubSearchResponse
import com.indrajeetsinhchauhan.githubrepoapp.data.model.GitHubUser
import com.indrajeetsinhchauhan.githubrepoapp.data.model.Repository
import com.indrajeetsinhchauhan.githubrepoapp.data.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(): List<GitHubUser>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): GitHubSearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetail

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String
    ): List<Repository>
}