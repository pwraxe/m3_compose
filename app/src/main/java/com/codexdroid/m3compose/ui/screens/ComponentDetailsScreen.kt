package com.codexdroid.m3compose.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80
import com.codexdroid.m3compose.ui.utils.ComponentData
import com.codexdroid.m3compose.ui.utils.State
import com.codexdroid.m3compose.ui.utils.fontMontserrat

@Preview
@Composable
private fun ComponentDetailsPreview() {
    ComponentDetailsScreen(ComponentData(), State.Available)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailsScreen(
    data: ComponentData,
    connectivityObserver: State,
    modifier: Modifier = Modifier) {

    val list = listOf("Output", "Code")
    var selectedIndex by remember { mutableIntStateOf(0) }
    var showAlert by remember { mutableStateOf(false) }
    val isConnected by remember { mutableStateOf(connectivityObserver == State.Available) }

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        SingleChoiceSegmentedButtonRow(
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
                        inactiveContentColor = Blue20,
                    )
                ) {

                    val selectedTextColor = if (selectedIndex == index) Color.White else Blue80

                    Text(
                        text = str,
                        color = selectedTextColor,
                        fontFamily = fontMontserrat
                    )
                }
            }
        }

        Row {

            TextButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(data.componentOutput))
                    Toast.makeText(
                        context,
                        context.getString(R.string.output_link_copied),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = modifier
                    //.weight(1f)
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null)
                Spacer(modifier = modifier.padding(start = 4.dp))
                Text(
                    text = stringResource(R.string.output_link),
                    color = Color.Black,
                    fontFamily = fontMontserrat)
            }


            TextButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(data.componentCode))
                    Toast.makeText(
                        context,
                        context.getString(R.string.code_link_copied),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = modifier
                    //.weight(1f)
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null)
                Spacer(modifier = modifier.padding(start = 4.dp))
                Text(
                    text = stringResource(R.string.code_link),
                    color = Color.Black,
                    fontFamily = fontMontserrat
                )
            }


            TextButton(
                onClick = { showAlert = true },
                modifier = modifier
                    .weight(1f)
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
                Spacer(modifier = modifier.padding(start = 10.dp))
                Text(
                    text = stringResource(R.string.share),
                    color = Color.Black,
                    fontFamily = fontMontserrat)
            }
        }

        if (isConnected) {

            if (selectedIndex == 0) {
                Box (modifier = modifier
                    .fillMaxSize().padding(10.dp)
                    .background(color = Color.White)) {

                    ShowOutput(url = data.componentOutput)
                }
            } else {
                selectedIndex = 1
                LoadCodeUrl(url = data.componentCode, modifier = modifier)
            }

        } else {

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.no_internet
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 200.dp, height = 120.dp)
                )

                Text(text = "No Internet Connection")
            }
        }

        if (showAlert) {
            OpenAlertDialog (
                modifier = modifier,
                onShare = {
                    showAlert = false
                    Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        if (it == 1) { //Output Link
                            putExtra(Intent.EXTRA_TEXT, data.componentOutput)
                        } else { //Code Link
                            putExtra(Intent.EXTRA_TEXT, data.componentCode)
                        }

                        ContextCompat.startActivities(
                            context,
                            arrayOf(
                                Intent.createChooser(
                                    this, "Share Link With", null
                                )
                            )
                        )
                    }
                },
                onCancel = {
                    showAlert = false
                }
            )
        }
    }
}


@Composable
fun ShowOutput(
    url: String,
    modifier: Modifier = Modifier
) {

    var loadingState by remember { mutableStateOf(State.Loading) }

    Box (modifier = modifier
        .fillMaxSize()
        .border(
            width = 2.dp,
            brush = Brush.linearGradient(listOf(Color.Red, Color.Black, Color.Yellow)),
            shape = RoundedCornerShape(20.dp)
        )) {


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().padding(4.dp),
            contentScale = ContentScale.Fit,
            onSuccess = {
                loadingState = State.Success
            },
            onLoading = {
                loadingState = State.Loading
            },
            onError = {
                loadingState = State.Error
            })

        when(loadingState) {

            State.Loading -> {
                Column(
                    modifier = modifier.align(alignment = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.loading),
                        modifier = modifier
                            .size(200.dp)
                            .padding(10.dp),
                        contentDescription = null)
                    Text(
                        text = stringResource(R.string.loading_image_please_wait),
                        fontFamily = fontMontserrat
                    )
                }
            }
            State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.not_found),
                    contentDescription = null)
            }
            State.Success -> {}
            else -> {}
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoadCodeUrl(url: String, modifier: Modifier) {

    AndroidView(modifier = modifier
        .fillMaxSize()
        .padding(10.dp)
        .border(
            width = 2.dp,
            color = Color.Black,
            shape = RoundedCornerShape(20.dp)
        ),
        factory = {
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

@Composable
fun OpenAlertDialog(
    modifier: Modifier,
    onShare: (Int) -> Unit,
    onCancel: () -> Unit ) {

    AlertDialog(
        onDismissRequest = { onCancel() },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text(text = "Cancel", color = Color.Black)
            }
        },
        text = {

            Column {
                OutlinedButton(
                    onClick = { onShare(1) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = "Share Output Link", color = Color.Black)
                }

                OutlinedButton(
                    onClick = { onShare(2) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = "Share Code Link", color = Color.Black)
                }

            }
        }
    )
}