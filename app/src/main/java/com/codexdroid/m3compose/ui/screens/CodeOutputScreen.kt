package com.codexdroid.m3compose.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
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
import com.codexdroid.m3compose.ui.theme.Blue40
import com.codexdroid.m3compose.ui.utils.ComponentData
import com.codexdroid.m3compose.ui.utils.State
import com.codexdroid.m3compose.ui.utils.componentsList
import com.codexdroid.m3compose.ui.utils.fontMontserrat

@Preview
@Composable
fun CodeOutputScreenPreview(modifier: Modifier = Modifier) {
    CodeOutputScreen(componentsList.first(),State.Success)
}

@Composable
fun CodeOutputScreen(
    data: ComponentData,
    connectivityObserver: State,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var selectedMenu by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val isConnected by remember { mutableStateOf(connectivityObserver == State.Available) }


    var outputBackColor by remember { mutableStateOf(Blue40) }
    var codeBackColor by remember { mutableStateOf(Blue20) }


    val animateOutputBackColor = animateColorAsState(
        targetValue = outputBackColor,
        animationSpec = tween(durationMillis = 300), label = ""
    ).value

    val animateCodeBackColor = animateColorAsState(
        targetValue = codeBackColor,
        animationSpec = tween(durationMillis = 300), label = ""
    ).value


    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column {
            Column(modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .weight(1f)) {

                if (isConnected) {
                    if (selectedMenu == 0) {
                        LoadOutputUrl(url = data.componentOutput, modifier = modifier)
                    }
                    else {
                        LoadCodeUrl(url = data.componentCode, modifier = modifier)
                    }
                }
                else {
                    ShowNoInternet()
                }
            }

            BottomAppBar(
                containerColor = Blue20,
                actions = {

                    Column (modifier = modifier
                        .clickable {
                            selectedMenu = 0
                            outputBackColor = Blue40
                            codeBackColor = Blue20
                        }
                        .background(
                            color = animateOutputBackColor,//if (selectedMenu == 0) Blue40 else Blue20,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_phone),
                            contentDescription = null)
                        Text(
                            text = stringResource(R.string.output),
                            fontFamily = fontMontserrat)
                    }

                    Column (
                        modifier = modifier
                            .clickable {
                                selectedMenu = 1
                                outputBackColor = Blue20
                                codeBackColor = Blue40
                            }
                            .background(
                                color = animateCodeBackColor, //if (selectedMenu == 1) Blue40 else Blue20,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_code),
                            contentDescription = null)
                        Text(
                            text = stringResource(R.string.code),
                            fontFamily = fontMontserrat
                        )
                    }

                    VerticalDivider(
                        thickness = 1.dp,
                        color = Color.Gray,
                        modifier = modifier.padding(16.dp)
                    )

                    Column (
                        modifier = modifier
                            .clickable {
                                if (selectedMenu == 0) {
                                    clipboardManager.setText(AnnotatedString(data.componentOutput))
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.output_link_copied),
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    clipboardManager.setText(AnnotatedString(data.componentCode))
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.code_link_copied),
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = null)


                        val linkOf = stringResource(id = if (selectedMenu == 0) R.string.output_link else R.string.code_link)
                        Text(
                            text = linkOf,
                            fontFamily = fontMontserrat
                        )
                    }
                },

                floatingActionButton = {
                    FloatingActionButton(onClick = { showDialog = true }) {
                        Icon(
                            imageVector = Icons.Rounded.Share,
                            contentDescription = null)
                    }
                }, modifier = modifier
            )
        }
    }

    if (showDialog) {
        OpenAlertDialog(
            modifier = modifier,
            onShare = {
                showDialog = false
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
            }, onCancel = {
                showDialog = false
            }
        )
    }
}

@Composable
fun ShowNoInternet(modifier: Modifier = Modifier) {
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

        Text(text = stringResource(R.string.no_internet_connection))
    }
}



@Composable
fun LoadOutputUrl(url: String, modifier: Modifier = Modifier) {

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
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
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
                Text(
                    text = stringResource(R.string.cancel),
                    color = Color.Black,
                    fontFamily = fontMontserrat
                )
            }
        },
        text = {

            Column {
                OutlinedButton(
                    onClick = { onShare(1) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.share_output_link),
                        color = Color.Black,
                        fontFamily = fontMontserrat
                    )
                }

                OutlinedButton(
                    onClick = { onShare(2) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.share_code_link),
                        color = Color.Black,
                        fontFamily = fontMontserrat
                    )
                }

            }
        }
    )
}