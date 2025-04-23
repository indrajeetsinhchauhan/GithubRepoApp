package com.indrajeetsinhchauhan.githubrepoapp.ui.userlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indrajeetsinhchauhan.githubrepoapp.databinding.ActivityUserListBinding
import com.indrajeetsinhchauhan.githubrepoapp.ui.userdetails.UserDetailActivity

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var viewModel: UserListViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        adapter = UserListAdapter {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("username", it.login)
            startActivity(intent)
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        viewModel.users.observe(this) { users ->
            if (users.isNotEmpty()) {
                adapter.setUsers(users)
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.etSearch.addTextChangedListener {
            val text = it.toString()
            if (text.isEmpty()) {
                viewModel.resetToOriginalList()
            }
            viewModel.searchUsers(text)
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchUsers(query)
                }
                hideKeyboardAndClearFocus()
                true
            } else {
                false
            }
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchUsers(query)
            }
            hideKeyboardAndClearFocus()
        }

        binding.rvUsers.setOnTouchListener { _, _ ->
            hideKeyboardAndClearFocus()
            false
        }
    }

    private fun hideKeyboardAndClearFocus() {
        val imm =
            getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        binding.etSearch.clearFocus()
    }
}