package com.watsidev.producto2.presentation.screens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun MenuScreen(
    onNavigateClick: (Any) -> Unit,
    listApps: List<Apps>
) {
    ScalingLazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listApps.size) { app ->
            AppButton(
                app = listApps[app],
                onClickNavigate = { route ->
                    onNavigateClick(route)
                },
                modifier = Modifier.fillParentMaxWidth()
            )
        }
    }
}

@Composable
fun AppButton(
    app: Apps,
    onClickNavigate: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClickNavigate(app.route) },
        shape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 25.dp, topStart = 25.dp, bottomStart = 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF2F2F2),
            contentColor = Color(0xFF2E2E2E)
        ),
        modifier = modifier
            .shadow(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
        ) {
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = app.icon,
                contentDescription = app.name,
            )
            Text(
                text = app.name,
            )
        }
    }
}