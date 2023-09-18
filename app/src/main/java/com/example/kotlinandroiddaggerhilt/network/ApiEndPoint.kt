package com.example.kotlinandroiddaggerhilt.network

import com.example.kotlinandroiddaggerhilt.models.GithubRepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("repositories")
    suspend fun getAllRepo() : Response<List<GithubRepositoryModel>>
}