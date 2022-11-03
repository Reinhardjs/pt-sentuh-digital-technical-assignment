package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mainViewModel.randomJoke.observe(this) { joke ->
//            if (joke != null) {
//                when (joke) {
//                    is Resource.Loading -> Toast.makeText(
//                        applicationContext,
//                        "Loading",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    is Resource.Success -> Toast.makeText(
//                        applicationContext,
//                        joke.data?.value,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    is Resource.Error -> Toast.makeText(
//                        applicationContext,
//                        "Error",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }

        mainViewModel.searchJokes("about").observe(this) { jokes ->
            if (jokes != null) {
                when (jokes) {
                    is Resource.Loading -> Toast.makeText(
                        applicationContext,
                        "Loading",
                        Toast.LENGTH_SHORT
                    ).show()
                    is Resource.Success -> Toast.makeText(
                        applicationContext,
                        jokes.data.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    is Resource.Error -> Log.d("MainActivity", "Error : " + jokes.message)
                }
            }
        }
    }
}