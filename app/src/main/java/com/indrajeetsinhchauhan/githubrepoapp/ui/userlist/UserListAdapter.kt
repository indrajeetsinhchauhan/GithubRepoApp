package com.indrajeetsinhchauhan.githubrepoapp.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indrajeetsinhchauhan.githubrepoapp.data.model.GitHubUser
import com.indrajeetsinhchauhan.githubrepoapp.databinding.ItemUserBinding

class UserListAdapter(private val onClick: (GitHubUser) -> Unit) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val users = mutableListOf<GitHubUser>()

    fun setUsers(newList: List<GitHubUser>) {
        users.clear()
        users.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GitHubUser) {
            binding.tvUsername.text = user.login
            Glide.with(binding.root.context)
                .load(user.avatar_url)
                .circleCrop()
                .into(binding.imgAvatar)
            binding.root.setOnClickListener { onClick(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

}
