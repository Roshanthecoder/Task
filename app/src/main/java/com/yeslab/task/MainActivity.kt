package com.yeslab.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.yeslab.task.adapter.MovieAdapter
import com.yeslab.task.databinding.ActivityMainBinding
import com.yeslab.task.model.MovCharacterList
import com.yeslab.task.network.ApiClient
import com.yeslab.task.network.ApiService
import com.yeslab.task.util.TAG
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val adapter = MovieAdapter(this, arrayListOf())
    val link =
        "http://pandora.yilstaging.com/writable/uploads/20210127/1611811599_2ac19cd41e8387119d7e.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerviewSet() //setRecyclerView
        fetchMovieCharacters() //Update adapter through api data
        onClickEvent() //it handles onclick listener
    }

    private fun onClickEvent() {
        binding.btnDesign.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
        binding.btnShareUrl.setOnClickListener {
            shareUrl(link)
        }
    }

    private fun shareUrl(url: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share URL"))
    }

    private fun recyclerviewSet() {
        binding.recyCharItem.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyCharItem.adapter = adapter
    }

    private fun fetchMovieCharacters() {
        lifecycleScope.launch {
            binding.progLoader.visibility = View.VISIBLE
            try {
                val response = ApiClient.retrofit
                    .create(ApiService::class.java)
                    .getMovieList()

                if (response.isSuccessful && response.body() != null) {
                    adapter.updateList(response.body()!!)
                    Log.d(TAG, "${response.body()}")
                    binding.progLoader.visibility = View.GONE
                } else {
                    Toast.makeText(this@MainActivity, "response Unsucessfull", Toast.LENGTH_SHORT)
                        .show()
                    binding.progLoader.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.d(TAG, "exception ${e.localizedMessage}")
                binding.progLoader.visibility = View.GONE
            }
        }
    }
}