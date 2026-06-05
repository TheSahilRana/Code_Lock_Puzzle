package com.example.codelockpuzzle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun first(navcontroller: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFF9EE84D))) {
    Column(modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(onClick = { navcontroller.navigate("codescreen") }, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF71A4),
            contentColor = Color.White
        )) {
            Text("START")
        }
    }
}}