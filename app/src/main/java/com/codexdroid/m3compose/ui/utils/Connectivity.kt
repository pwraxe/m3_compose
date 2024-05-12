package com.codexdroid.m3compose.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

enum class State {
    Available, Unavailable, Losing, Lost,
    Loading, Error, Success
}
interface ConnectivityObserver {
    fun observe(): Flow<State>

}

class NetworkConnectivityObserver(context: Context): ConnectivityObserver {
    private val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<State> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(State.Available) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(State.Unavailable) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(State.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(State.Lost) }
                }
            }

            manager.registerDefaultNetworkCallback(callback)
            awaitClose {
                manager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()

    }

}

