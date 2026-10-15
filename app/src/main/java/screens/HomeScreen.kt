package com.example.studentregistration.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onRegisterClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Student Registration App",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Select an option below",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF388E3C)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD STUDENT")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onSearchClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF388E3C)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("FIND STUDENT")
        }
    }
}