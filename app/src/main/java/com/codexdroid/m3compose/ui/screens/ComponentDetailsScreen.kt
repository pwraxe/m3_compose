package com.codexdroid.m3compose.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.activities.ScreenViewModel
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80
import com.codexdroid.m3compose.ui.utils.AppConstants
import com.codexdroid.m3compose.ui.utils.ComponentData


@Preview
@Composable
private fun ComponentDetailsPreview() {
    ComponentDetailsScreen(ComponentData())
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailsScreen(
    data: ComponentData,
    modifier: Modifier = Modifier) {

    Log.d("AXE","DATA : ${data.componentName}")

    val list = listOf("Output","Code")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column (modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        SingleChoiceSegmentedButtonRow (
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            list.forEachIndexed { index, str ->
                SegmentedButton(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    shape = SegmentedButtonDefaults
                        .itemShape(index = index, count = list.size),
                    border = BorderStroke(width = 1.dp, color = Blue80),
                    colors = SegmentedButtonDefaults.colors().copy(
                        activeBorderColor = Blue80,
                        activeContainerColor = Blue80,
                        activeContentColor = Blue80,
                        inactiveBorderColor = Blue20,
                        inactiveContainerColor = Blue20,
                        inactiveContentColor = Blue20)) {

                    val selectedTextColor = if (selectedIndex == index) Color.White else Blue80

                    Text(
                        text = str,
                        color = selectedTextColor
                    )
                }
            }
        }

        val url = if (selectedIndex == 0) data.componentOutput
        else data.componentCode

        LoadWebUrl(url,modifier)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoadWebUrl(url:String, modifier: Modifier) {
    AndroidView(modifier = modifier.fillMaxSize(), factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.builtInZoomControls = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

