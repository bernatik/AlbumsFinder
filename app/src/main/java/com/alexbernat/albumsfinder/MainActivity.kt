package com.alexbernat.albumsfinder

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexbernat.albumsfinder.databinding.ActivityMainBinding
import com.alexbernat.albumsfinder.presentation.AlbumsAdapter
import com.alexbernat.albumsfinder.presentation.ImageLoader
import com.alexbernat.albumsfinder.presentation.MainViewModel
import com.alexbernat.albumsfinder.presentation.model.UiState
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()

    private val albumsAdapter by lazy {
        AlbumsAdapter(imageLoader)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeState()

        with(binding) {
            albumsRecyclerView.adapter = albumsAdapter
            albumsRecyclerView.itemAnimator = null
            searchInputEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search()
                }
                false
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { albums ->
                    renderState(albums)
                }
            }
        }
    }

    private fun renderState(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                with(binding) {
                    labelTextView.visibility = View.GONE
                    albumsRecyclerView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }

            is UiState.Error -> {
                with(binding) {
                    progressBar.visibility = View.GONE
                    albumsRecyclerView.visibility = View.GONE
                    labelTextView.text = getString(uiState.data.msgRes)
                    labelTextView.visibility = View.VISIBLE
                }
            }

            is UiState.SearchResult -> {
                albumsAdapter.submitList(uiState.albums) {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        labelTextView.visibility = View.GONE
                        albumsRecyclerView.visibility = View.VISIBLE
                    }
                }
            }

            else -> {}
        }
    }

    private fun search() {
        val query = binding.searchInputEditText.text?.toString() ?: ""
        viewModel.search(query)
    }
}