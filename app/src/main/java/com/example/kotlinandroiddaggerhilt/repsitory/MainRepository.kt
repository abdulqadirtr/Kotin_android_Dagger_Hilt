package com.example.kotlinandroiddaggerhilt.repsitory

import com.example.kotlinandroiddaggerhilt.network.ApiEndPoint
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiEndPoint: ApiEndPoint) {

    suspend fun getRepo() = apiEndPoint.getAllRepo()

}