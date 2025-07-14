package com.watsidev.producto2.presentation.screens.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun AtaquesMenu(ataques: List<String>, onAtaqueSeleccionado: (String) -> Unit) {
    Column {
        ataques.forEach { ataque ->
            Button(
                onClick = { onAtaqueSeleccionado(ataque) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Text(ataque)
            }
        }
    }
}
