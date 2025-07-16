package com.watsidev.producto2.presentation.screens.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Card

@Composable
fun MessagesScreen(messages: List<Message> = sampleMessages, onClick: () -> Unit) {
    ScalingLazyColumn(
        modifier = Modifier
            .clickable{ onClick() }
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(messages) { message ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                onClick = { /* Abrir conversación */ }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color(0xFFCCFF00),
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = message.sender,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                        Text(
                            text = message.content,
                            color = Color(0xFF2E2E2E),
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Text(
                        text = message.time,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

// Modelo de datos
data class Message(val sender: String, val content: String, val time: String)

// Mensajes de ejemplo
val sampleMessages = listOf(
    Message("Ana", "¿Vas a venir hoy?", "5m"),
    Message("Luis", "Ya terminé el archivo", "12m"),
    Message("Sofía", "Nos vemos en la tarde", "1h"),
    Message("Carlos", "Reunión cancelada", "2h"),
    Message("Mamá", "Te dejé comida en la mesa ❤️", "3h")
)
