package com.example.kotlinandroiddaggerhilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinandroiddaggerhilt.R
import com.example.kotlinandroiddaggerhilt.databinding.ItemsRepoBinding
import com.example.kotlinandroiddaggerhilt.models.GithubRepositoryModel

class RepoAdapter() : RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.MyViewHolder {
        val itemBinding = ItemsRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepoAdapter.MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<GithubRepositoryModel>(){
        override fun areItemsTheSame(oldItem: GithubRepositoryModel, newItem: GithubRepositoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubRepositoryModel, newItem: GithubRepositoryModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<GithubRepositoryModel>) = differ.submitList(list)

    inner class MyViewHolder(private val itemBinding: ItemsRepoBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            val item = differ.currentList[position]
            itemBinding.nameTextView.text = "Name: ${item.name}" // Set the name property
            itemBinding.descTextView.text = "Full Name: ${item.full_name}" // Set the full_name property
            itemBinding.createdDateTextView.text = "Owner: ${item.owner}" // Set the owner property
        }

    }
}