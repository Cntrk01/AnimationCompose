package com.mckapp.animationcompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonScreen(
    onClick : () -> Unit,
    icon : ImageVector,
    label : String,
    backgroundColor : Color
) {
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color=backgroundColor,
        contentColor = Color.Black
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = label, fontSize = 24.sp, fontFamily = FontFamily.Serif)
            
            Spacer(modifier = Modifier.size(16.dp))

            IconButton(onClick = { onClick() }) {
                Icon(imageVector = icon, contentDescription = "",modifier=Modifier.size(60.dp))

            }
        }
    }
}