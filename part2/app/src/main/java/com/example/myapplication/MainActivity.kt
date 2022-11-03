package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.ui.JokeAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val jokeAdapter = JokeAdapter()
        with(binding.rvJoke) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = jokeAdapter
        }

        mainViewModel.randomJoke.observe(this) { joke ->
            if (joke != null) {
                when (joke) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (joke.data != null)
                            jokeAdapter.setData(listOf(joke.data))
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        binding.viewError.tvError.text = joke.message ?: "Something wrong"
                    }
                }
            }
        }

        mainViewModel.searchJokes("about").observe(this) { jokes ->
            if (jokes != null) {
                when (jokes) {
                    is Resource.Loading -> Log.d("MainActivity", "Loading Jokes Search Request")
                    is Resource.Success -> Log.d("MainActivity", jokes.data.toString())
                    is Resource.Error -> Log.d("MainActivity", "Error : " + jokes.message)
                }
            }
        }
    }
}