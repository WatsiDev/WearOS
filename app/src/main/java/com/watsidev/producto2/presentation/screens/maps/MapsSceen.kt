package com.watsidev.producto2.presentation.screens.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.watsidev.producto2.R

@Composable
fun MapLibreScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painterResource(R.drawable.aguja),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable{ onBack() }
                    .size(35.dp)
            )
        }
    }
}

