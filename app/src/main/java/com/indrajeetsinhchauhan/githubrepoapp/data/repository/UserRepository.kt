package com.indrajeetsinhchauhan.githubrepoapp.data.repository

import com.indrajeetsinhchauhan.githubrepoapp.data.api.RetrofitClient
import com.indrajeetsinhchauhan.githubrepoapp.data.model.GitHubUser

class UserRepository {
    suspend fun getUsers(): List<GitHubUser> {
        return RetrofitClient.apiService.getUsers()
    }

    suspend fun searchUsers(query: String): List<GitHubUser> {
        val response = RetrofitClient.apiService.searchUsers(query)
        return response.items.filter {
            it.login.contains(query, ignoreCase = true)
        }
    }

}