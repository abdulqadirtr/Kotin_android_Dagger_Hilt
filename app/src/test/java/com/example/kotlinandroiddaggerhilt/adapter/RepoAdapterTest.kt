package com.example.kotlinandroiddaggerhilt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinandroiddaggerhilt.databinding.ItemsRepoBinding
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RepoAdapterTest{

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockInflater: LayoutInflater

    @Mock
    private lateinit var mockParent: ViewGroup

    @Mock
    private lateinit var mockView: View // Mock a View object

    private lateinit var adapter: RepoAdapter

    @Before
    fun setBefore(){
        MockitoAnnotations.openMocks(this)
        adapter = RepoAdapter()
    }

    @Test
    fun onCreateViewHolderTest(){

        // Arrange
        `when`(mockContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockInflater)
        `when`(mockInflater.inflate(anyInt(), eq(mockParent), eq(false))).thenReturn(mockView) // Use a mocked View

        // Act
        val viewHolder = adapter.onCreateViewHolder(mockParent, 0)

        // Assert
        assertNotNull(viewHolder)
        assertTrue(viewHolder is RepoAdapter.MyViewHolder)


    }


    @After
    fun setAfter(){



    }





}