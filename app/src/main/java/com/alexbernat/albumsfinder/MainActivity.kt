package com.alexbernat.albumsfinder

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexbernat.albumsfinder.databinding.ActivityMainBinding
import com.alexbernat.albumsfinder.domain.model.Album
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
            is UiState.Loading -> binding.showLoading()
            is UiState.Error -> binding.showError(getString(uiState.data.msgRes))
            is UiState.SearchResult -> binding.showResult(uiState.albums)
            else -> {}
        }
    }

    private fun search() {
        val query = binding.searchInputEditText.text?.toString() ?: ""
        viewModel.search(query)
    }

    private fun ActivityMainBinding.showError(msg: String) {
        progressBar.visibility = View.GONE
        albumsRecyclerView.visibility = View.GONE
        labelTextView.text = msg
        labelTextView.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.showLoading() {
        labelTextView.visibility = View.GONE
        albumsRecyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.showResult(albums: List<Album>) {
        albumsAdapter.submitList(albums) {
            progressBar.visibility = View.GONE
            if (albums.isEmpty()) {
                showEmptyResultMsg()
            } else {
                labelTextView.visibility = View.GONE
                albumsRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun ActivityMainBinding.showEmptyResultMsg() {
        labelTextView.text = getString(R.string.empty_search_result)
        labelTextView.visibility = View.VISIBLE
        albumsRecyclerView.visibility = View.GONE
    }
}