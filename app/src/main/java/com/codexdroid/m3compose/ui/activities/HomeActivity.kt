package com.codexdroid.m3compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codexdroid.m3compose.ui.screens.ComponentDetailsScreen
import com.codexdroid.m3compose.ui.screens.ComponentScreen
import com.codexdroid.m3compose.ui.screens.HomeScreen
import com.codexdroid.m3compose.ui.screens.PlayGroundScreen
import com.codexdroid.m3compose.ui.theme.M3ComposeTheme
import com.codexdroid.m3compose.ui.utils.ConnectivityObserver
import com.codexdroid.m3compose.ui.utils.NetworkConnectivityObserver
import com.codexdroid.m3compose.ui.utils.SCREEN
import com.codexdroid.m3compose.ui.utils.State

class HomeActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(this)
        setContent {
            M3ComposeTheme {
                val status by connectivityObserver
                    .observe()
                    .collectAsState(
                        initial = State.Unavailable
                    )
                LetsStart(status)
            }
        }
    }
}

@Composable
fun LetsStart(
    state: State,
    navController: NavHostController = rememberNavController(),
    screenViewModel: ScreenViewModel = viewModel()) {

    NavHost(
        navController = navController,
        startDestination = SCREEN.HOME.name) {

        composable(SCREEN.HOME.name) {
            HomeScreen(
                onViewClicked = {
                    navController.navigate(SCREEN.COMPONENT.name)
                }, onPlayGroundClicked = {
                    navController.navigate(SCREEN.PLAYGROUND.name)
                })
        }
        composable(SCREEN.COMPONENT.name) {
            ComponentScreen(onCardClicked = {
                navController.navigate(SCREEN.COMPONENT_DETAILS.name)
            }, screenViewModel)
        }

        composable(SCREEN.COMPONENT_DETAILS.name) {
            val componentData by screenViewModel.componentData.collectAsState()
            ComponentDetailsScreen(data = componentData, connectivityObserver = state)
        }

        composable(SCREEN.PLAYGROUND.name) {
            PlayGroundScreen()
        }
    }
}