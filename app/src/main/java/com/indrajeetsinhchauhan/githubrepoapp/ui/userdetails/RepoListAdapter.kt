package com.indrajeetsinhchauhan.githubrepoapp.ui.userdetails

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indrajeetsinhchauhan.githubrepoapp.R
import com.indrajeetsinhchauhan.githubrepoapp.data.model.Repository
import com.indrajeetsinhchauhan.githubrepoapp.databinding.ItemRepoBinding

class RepoListAdapter : ListAdapter<Repository, RepoListAdapter.RepoViewHolder>(DiffCallback()) {

    inner class RepoViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.tvRepoName.text = repo.name
            val context = binding.root.context
            binding.tvRepoLangStars.text =
                context.getString(
                    R.string.repo_star_language_placeholder,
                    repo.stargazers_count,
                    repo.language ?: "Unknown"
                )
            binding.tvRepoDesc.text = repo.description ?: ""

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.html_url))
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(old: Repository, new: Repository) = old.name == new.name
        override fun areContentsTheSame(old: Repository, new: Repository) = old == new
    }
}
