package com.alexbernat.albumsfinder.presentation.model

import androidx.annotation.StringRes
import com.alexbernat.albumsfinder.R

enum class PresentationError(@StringRes val msgRes: Int) {
    NetworkError(R.string.err_network),
    QueryError(R.string.err_query),
    EmptyResultError(R.string.err_empty),
    UnknownError(R.string.err_unknown)
}