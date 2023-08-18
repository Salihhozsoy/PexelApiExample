package com.example.pexelapi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pexelapi.data.api.model.Photo
import com.example.pexelapi.data.state.PhotoListState
import com.example.pexelapi.databinding.ActivityMainBinding
import com.example.pexelapi.ui.photodetail.PhotoDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observePhotoListState()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("before")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getAllPhotos(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                println("after")
            }
        })
    }

    private fun observePhotoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.photoListState.collect {
                    when (it) {
                        PhotoListState.Idle -> {}
                        PhotoListState.Loading -> {}
                        PhotoListState.Empty -> {}
                        is PhotoListState.Result -> {
                            adapter = PhotoAdapter(this@MainActivity, it.photos, this@MainActivity::onClick)
                            binding.rvPhotos.adapter = adapter
                        }
                        PhotoListState.Error -> {}
                    }
                }
            }
        }
    }

    private fun onClick(photo: Photo) {
        val photoDetailFragment = PhotoDetailFragment(photo)
        photoDetailFragment.show(supportFragmentManager, null)
    }
}