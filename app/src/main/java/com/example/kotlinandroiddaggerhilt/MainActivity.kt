package com.example.kotlinandroiddaggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinandroiddaggerhilt.adapter.RepoAdapter
import com.example.kotlinandroiddaggerhilt.databinding.ActivityMainBinding
import com.example.kotlinandroiddaggerhilt.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: RepoAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = RepoAdapter()
        binding.rvRepo.layoutManager = LinearLayoutManager(this)
        binding.rvRepo.adapter = adapter


        initObserve()
    }

    private fun initObserve() {
        mainViewModel.repo.observe(this, Observer {
            val rootView = findViewById<View>(R.id.rootView)
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                    adapter.submitList(it.data!!)
                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvRepo.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                    //Snackbar.make(rootView, "Something went wrong",Snackbar.LENGTH_SHORT).show()

                }

            }

        })
    }
}