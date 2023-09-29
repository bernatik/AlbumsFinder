package com.alexbernat.albumsfinder.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexbernat.albumsfinder.databinding.ItemAlbumBinding
import com.alexbernat.albumsfinder.domain.model.Album

private val diffUtilCallback = object : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}

class AlbumsAdapter(
    private val imageLoader: ImageLoader
) : ListAdapter<Album, AlbumsAdapter.AlbumViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = currentList[position]
        holder.onBind(album)
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(album: Album) {
            with(binding) {
                album.imageUrl?.let {
                    imageLoader.loadImage(itemAlbumImageView, album.imageUrl)
                }
                itemAlbumTitleTextView.text = album.title
            }
        }
    }
}