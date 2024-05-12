package com.codexdroid.m3compose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.codexdroid.m3compose.ui.utils.State
import com.codexdroid.m3compose.ui.utils.componentsList


@Preview
@Composable
fun PlayGroundPreview() {
    PlayGroundScreen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PlayGroundScreen(modifier: Modifier = Modifier) {

    var loadingState by remember { mutableStateOf(State.Loading) }

    Box (modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        ShowOutput(url = componentsList.first().componentOutput)
    }
}

//@Composable
//fun ShowOutput(
//    url: String,
//    onLoading: () -> Unit,
//    onError: (Throwable) -> Unit,
//    onSuccess: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//
//    val painter = rememberAsyncImagePainter(
//        ImageRequest.Builder(LocalContext.current).data(data = url)
//            .apply(block = fun ImageRequest.Builder.() {
//            listener(
//                onStart = { onLoading.invoke() },
//                onError = { _, ex -> onError.invoke(ex.throwable) },
//                onSuccess = { _, _ -> onSuccess.invoke() }
//            )
//        }).build()
//    )
//
//    Box (modifier = modifier.fillMaxSize()) {
//
//        Image(
//            painter = painter,
//            contentDescription = null,
//            modifier = modifier.align(Alignment.Center))
//
//        when(painter.state) {
//
//            is AsyncImagePainter.State.Error -> {
//                Image(
//                    painter = painterResource(id = R.drawable.loading),
//                    contentDescription = null)
//            }
//            is AsyncImagePainter.State.Loading -> {
//                Image(
//                    painter = painterResource(id = R.drawable.loading),
//                    modifier = modifier
//                        .size(200.dp)
//                        .align(alignment = Alignment.Center),
//                    contentDescription = null)
//            }
//            is AsyncImagePainter.State.Success -> {
//
//            }
//
//            AsyncImagePainter.State.Empty -> TODO()
//        }
//    }
//}