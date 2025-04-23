package com.indrajeetsinhchauhan.githubrepoapp.ui.userdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indrajeetsinhchauhan.githubrepoapp.data.api.RetrofitClient
import com.indrajeetsinhchauhan.githubrepoapp.data.model.Repository
import com.indrajeetsinhchauhan.githubrepoapp.data.model.UserDetail
import kotlinx.coroutines.launch

class UserDetailViewModel: ViewModel() {
    val userDetail = MutableLiveData<UserDetail>()
    val repositories = MutableLiveData<List<Repository>>()

    fun fetchUserDetails(username: String) {
        viewModelScope.launch {
            try {
                val user = RetrofitClient.apiService.getUserDetail(username)
                val repos = RetrofitClient.apiService.getUserRepos(username)
                    .filter { !it.fork }
                userDetail.postValue(user)
                repositories.postValue(repos)
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}