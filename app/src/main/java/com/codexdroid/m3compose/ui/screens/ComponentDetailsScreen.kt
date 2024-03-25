package com.codexdroid.m3compose.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80
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
    
    val list = listOf("Output","Code")
    var selectedIndex by remember { mutableIntStateOf(0) }
    var showAlert by remember { mutableStateOf(false) }

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

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
                    .padding(6.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null)
                Spacer(modifier = modifier.padding(start = 10.dp))
                Text(text = "Output Link", color = Color.Black)
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
                    .padding(6.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(painter = painterResource(id = R.drawable.ic_copy), contentDescription = null)
                Spacer(modifier = modifier.padding(start = 10.dp))
                Text(text = "Code Link", color = Color.Black)
            }


            TextButton(
                onClick = { showAlert = true },
                modifier = modifier
                    .weight(1f)
                    .padding(6.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
                Spacer(modifier = modifier.padding(start = 10.dp))
                Text(text = "Share", color = Color.Black)
            }
        }

        if (showAlert) {
            OpenAlertDialog(
                modifier = modifier,
                onShare = {
                    showAlert = false

                    Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        if (it == 1) { //Output Link
                            putExtra(Intent.EXTRA_TEXT,data.componentOutput)
                        } else { //Code Link
                            putExtra(Intent.EXTRA_TEXT,data.componentCode)
                        }

                        ContextCompat.startActivities(context,
                            arrayOf(
                                Intent.createChooser(this,"Share Link With",null
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


        val url = if (selectedIndex == 0) data.componentOutput else data.componentCode
        LoadCodeUrl(url = url, modifier = modifier)
    }
}



@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoadCodeUrl(url:String, modifier: Modifier) {

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
    onCancel: () -> Unit) {

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
                   modifier = modifier.fillMaxWidth()) {
                   Text(text = "Share Output Link", color = Color.Black)
               }

               OutlinedButton(
                   onClick = { onShare(2) },
                   modifier = modifier.fillMaxWidth()) {
                   Text(text = "Share Code Link", color = Color.Black)
               }

           }
        }
    )
}