package com.codexdroid.m3compose.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.codexdroid.m3compose.ui.screens.SplashScreen
import com.codexdroid.m3compose.ui.theme.M3ComposeTheme
import kotlinx.coroutines.delay

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M3ComposeTheme {

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    SplashScreen()
                }
                val intent = Intent(LocalContext.current, HomeActivity::class.java)
                LaunchedEffect(key1 = true) {
                    delay(2000)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}