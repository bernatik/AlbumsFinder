package com.alexbernat.albumsfinder.data.network

import android.util.Log
import io.ktor.client.plugins.logging.Logger

class AndroidHttpLogger : Logger {
    companion object {
        private const val logTag = "HttpLogger"
    }

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}