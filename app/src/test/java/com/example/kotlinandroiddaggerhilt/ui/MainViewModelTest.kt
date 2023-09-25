package com.example.kotlinandroiddaggerhilt.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlinandroiddaggerhilt.getOrAwaitValue
import com.example.kotlinandroiddaggerhilt.models.GithubRepositoryModel
import com.example.kotlinandroiddaggerhilt.network.ApiEndPoint
import com.example.kotlinandroiddaggerhilt.repsitory.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

class MainViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    private lateinit var viewModel: MainViewModel



    @Mock
    private lateinit var githubApi: ApiEndPoint



    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(mainRepository)

    }

    @Test
    fun getAllRepoSuccessTest() = runTest {

        val owner1 = GithubRepositoryModel.Owner("Owner 1", 1, "https://example.com/avatar1.jpg")
        val owner2 = GithubRepositoryModel.Owner("Owner 2", 2, "https://example.com/avatar2.jpg")

        val mockRepositories = arrayListOf(
            GithubRepositoryModel(1, "Repo 1", "Description 1", "owner1",true,owner1,"test.com","test",true,"test.com","","",""),
            GithubRepositoryModel(2, "Repo 2", "Description 2", "owner2", true, owner2,"test.com","test",true,"test.com","","","")
        )

        //`when`(mainRepository.getRepo()).thenReturn(mockRepositories)
// Mock the mainRepository.getRepo() method to return the mockResponse
        `when`(mainRepository.getRepo()).thenReturn(Response.success(mockRepositories))

        testDispatcher.scheduler.advanceUntilIdle()
        val repositories = viewModel.repo.getOrAwaitValue()

        assertEquals(mockRepositories, repositories.data)
    }

    @After
    fun setClose(){
        Dispatchers.shutdown()
    }

}