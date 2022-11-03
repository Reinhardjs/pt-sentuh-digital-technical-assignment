package com.example.myapplication

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.core.data.Resource
import com.example.myapplication.core.ui.JokeAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.widget.EditText




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

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
                        Toast.makeText(
                            applicationContext,
                            "You just seen one random joke. \nTo get more jokes please search...",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        binding.viewError.tvError.text = joke.message ?: "Something wrong"
                    }
                }
            }
        }

        binding.search.setOnClickListener {
            binding.tilSearchJokes.editText?.clearFocus()
            jokeAdapter.setData(listOf())
            mainViewModel.searchJokes(binding.tilSearchJokes.editText?.text.toString())
                .observe(this) { jokes ->
                    if (jokes != null) {
                        when (jokes) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                jokeAdapter.setData(jokes.data)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.GONE
                                binding.viewError.tvError.text = jokes.message ?: "Something wrong"
                            }
                        }
                    }
                }
        }
    }
}