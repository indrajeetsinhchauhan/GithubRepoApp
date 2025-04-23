package com.indrajeetsinhchauhan.githubrepoapp.ui.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.indrajeetsinhchauhan.githubrepoapp.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel
    private lateinit var repoAdapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return

        viewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]
        repoAdapter = RepoListAdapter()

        binding.rvRepos.layoutManager = LinearLayoutManager(this)
        binding.rvRepos.adapter = repoAdapter

        viewModel.userDetail.observe(this) { user ->
            binding.tvUsernameDetail.text = user.login
            binding.tvFullName.text = user.name ?: ""
            binding.tvFollowers.text = "Followers: ${user.followers} | Following: ${user.following}"
            Glide.with(this).load(user.avatar_url).circleCrop()
                .into(binding.imgAvatarDetail)
        }

        viewModel.repositories.observe(this) {
            repoAdapter.submitList(it)
        }

        viewModel.fetchUserDetails(username)
    }
}