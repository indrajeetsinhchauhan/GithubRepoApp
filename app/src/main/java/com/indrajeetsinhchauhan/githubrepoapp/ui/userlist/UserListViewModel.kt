package com.indrajeetsinhchauhan.githubrepoapp.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indrajeetsinhchauhan.githubrepoapp.data.model.GitHubUser
import com.indrajeetsinhchauhan.githubrepoapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {
    val _users = MutableLiveData<List<GitHubUser>>()
    private var originalUserList: List<GitHubUser> = emptyList()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val repository = UserRepository()
    val users: LiveData<List<GitHubUser>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                originalUserList = userList
                _users.value = userList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchUsers(query: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = repository.searchUsers(query)
                _users.postValue(result.toList())
            } catch (e: Exception) {
                _users.postValue(emptyList())
            } finally {
                _loading.value = false
            }
        }
    }

    fun resetToOriginalList() {
        _users.value = originalUserList
    }
}