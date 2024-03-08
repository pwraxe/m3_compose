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
                    delay(3000)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SplashScreen()
}

@Composable
private fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_foreground),
            modifier = Modifier
                .size(260.dp)
                .align(alignment = Alignment.Center),
            contentDescription = null)

        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Inspired from",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "M3 & Compose Material Catalog App",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}
