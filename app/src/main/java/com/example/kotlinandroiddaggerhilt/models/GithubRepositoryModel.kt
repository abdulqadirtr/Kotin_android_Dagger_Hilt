package com.example.kotlinandroiddaggerhilt.models

data class GithubRepositoryModel(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val owner: Owner,
    val html_url: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    val created_at: String,
    val updated_at: String,
    val pushed_at: String?,
    val homepage: String?=null,
    val size: Int?=null,
    val stargazers_count: Int?=null,
    val watchers_count: Int?=null,
    val language: String?=null,
    val forks_count: Int?=null,
    val open_issues_count: Int?=null,
    val default_branch: String?=null
) {
    data class Owner(
        val login: String,
        val id: Int,
        val node_id: String,
        val avatar_url: String?=null,
        val gravatar_id: String?=null,
        val url: String?=null,
        val received_events_url: String?=null,
        val type: String?=null
    )
}
