package com.codexdroid.m3compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codexdroid.m3compose.R


//@Preview
//@Composable
//fun Preview() {
//    SplashScreen()
//}

@Composable
 fun SplashScreen() {
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
                text = stringResource(R.string.inspired_from),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.m3and_its_App),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}
