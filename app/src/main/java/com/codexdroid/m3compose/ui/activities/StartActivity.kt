package com.codexdroid.m3compose.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codexdroid.m3compose.R
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