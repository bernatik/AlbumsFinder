package com.alexbernat.albumsfinder.di

import com.alexbernat.albumsfinder.presentation.GlideImageLoader
import com.alexbernat.albumsfinder.presentation.ImageLoader
import com.alexbernat.albumsfinder.presentation.MainViewModel
import com.alexbernat.albumsfinder.data.ITunesAlbumsRepository
import com.alexbernat.albumsfinder.domain.AlbumsRepository
import com.alexbernat.albumsfinder.domain.FindAlbumsUseCase
import com.alexbernat.albumsfinder.domain.ValidateQueryUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ImageLoader> {
        GlideImageLoader()
    }
    single {
        FindAlbumsUseCase(
            albumsRepository = get()
        )
    }
    single {
        ValidateQueryUseCase()
    }
    single<AlbumsRepository> {
        ITunesAlbumsRepository(
            albumsAPI = get()
        )
    }
    viewModel {
        MainViewModel(
            searchUseCase = get(),
            validateQueryUseCase = get()
        )
    }
}