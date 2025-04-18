package com.assignment.androidapp.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkMonitor(context: Context,
    private val onConnected: (Boolean) -> Unit
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun register() {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onConnected(true)  // Device has come online
            }

            override fun onLost(network: Network) {
                onConnected(false) // Device went offline
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
}


