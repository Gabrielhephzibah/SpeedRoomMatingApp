package com.cherish.speedroommatingapp.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.*
import android.os.Build

@Suppress("DEPRECATION")
object ConnectionStatus  {
    var connection = false
    private lateinit var connectivityCallback: ConnectivityManager.NetworkCallback
    private  lateinit var connectivityManager : ConnectivityManager

    fun isOnline(context: Context) : Boolean {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(getConnectivityCallback())
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> buidVersionLollipop()
            else -> {
                updateConnection()
            }
        }
        return connection
    }

    private fun getConnectivityCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    connection = true
                }

                override fun onLost(network: Network) {
                    connection = true
                }
            }
            return connectivityCallback
        } else {
            throw IllegalAccessError("EXCEPTION")
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun buidVersionLollipop() {
        val builder = NetworkRequest.Builder()
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(builder.build(), getConnectivityCallback())
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        connection = activeNetwork?.isConnected == true
    }

}