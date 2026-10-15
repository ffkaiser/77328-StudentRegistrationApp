package com.example.studentregistration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SearchPage(onBackClick: () -> Unit) {

    val context = LocalContext.current

    val database = remember {
        StudentDatabase.getDatabase(context)
    }

    val scope = rememberCoroutineScope()

    var studentId by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Student Search",
            fontSize = 25.sp
        )

        OutlinedTextField(
            value = studentId,
            onValueChange = {
                studentId = it
            },
            label = {
                Text("Student ID")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {

                val id = studentId.toIntOrNull()

                if (
                    id == null ||
                    id <= 0
                ) {

                    result =
                        "Please enter a valid student ID"

                } else {

                    scope.launch {

                        try {

                            val student =
                                withContext(Dispatchers.IO) {
                                    database
                                        .studentDao()
                                        .findStudent(id)
                                }

                            if (student != null) {

                                result =
                                    "Name: ${student.name}\n" +
                                            "Course: ${student.course}\n" +
                                            "Year: ${student.year}"

                            } else {

                                result =
                                    "Student not found"
                            }

                        } catch (e: Exception) {

                            result =
                                "Error searching for student"
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF388E3C)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("FIND STUDENT")
        }

        if (result.isNotEmpty()) {
            Text(text = result)
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}