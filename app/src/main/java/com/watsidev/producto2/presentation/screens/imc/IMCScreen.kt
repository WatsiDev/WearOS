package com.watsidev.producto2.presentation.screens.imc

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn

@Composable
fun IMCScreen() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf<Double?>(null) }
    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
    ) {
        item {
            Text(
                text = "Calculadora de IMC",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
        item {
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = {
                    Text(
                        "Peso (kg)"
                    )
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )
        }
        item {
            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = {
                    Text(
                        "Altura (m)",
                    )
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )
        }
        item {
            Button(
                onClick = {
                    val pesokg = peso.toDoubleOrNull()
                    val alturaM = altura.toDoubleOrNull()
                    if (pesokg != null && alturaM != null && alturaM > 0) {
                        imc = pesokg / (alturaM * alturaM)
                    } else {
                        imc = null
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF2C2C2E),
                        shape = MaterialTheme.shapes.large
                    ), colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Calcular IMC", color = Color.Black)
            }
        }
        item {
            Text(
                text = when {
                    imc == null -> "Ingrese valores validos"
                    imc!! < 18.5 -> "Bajo peso: %.2f".format(imc)
                    imc!! < 25.0 -> "Peso normal: %.2f".format(imc)
                    imc!! < 30.0 -> "Sobrepeso: %.2f".format(imc)
                    else -> "Obesidad: %.2f".format(imc)
                },
                color = Color.White,
            )
        }
    }
}