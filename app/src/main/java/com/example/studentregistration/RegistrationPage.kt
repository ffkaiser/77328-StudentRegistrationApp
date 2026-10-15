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
fun RegistrationPage(onBackClick: () -> Unit) {

    val context = LocalContext.current

    val database = remember {
        StudentDatabase.getDatabase(context)
    }

    val scope = rememberCoroutineScope()

    var name by remember {
        mutableStateOf("")
    }

    var course by remember {
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Student Registration",
            fontSize = 25.sp
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Student Name")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = course,
            onValueChange = {
                course = it
            },
            label = {
                Text("Course")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = year,
            onValueChange = {
                year = it
            },
            label = {
                Text("Year (1-4)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {

                val yearNumber = year.toIntOrNull()

                if (
                    name.isBlank() ||
                    course.isBlank() ||
                    year.isBlank()
                ) {

                    message = "Please fill in all fields"

                } else if (
                    yearNumber == null ||
                    yearNumber !in 1..4
                ) {

                    message = "Year must be between 1 and 4"

                } else {

                    scope.launch {

                        try {

                            val student = Student(
                                name = name.trim(),
                                course = course.trim(),
                                year = yearNumber
                            )

                            val studentId = withContext(Dispatchers.IO) {
                                database
                                    .studentDao()
                                    .insertStudent(student)
                            }

                            message =
                                "Student registered! ID: $studentId"

                            name = ""
                            course = ""
                            year = ""

                        } catch (e: Exception) {

                            message =
                                "Error saving student"
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF388E3C)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD STUDENT")
        }

        if (message.isNotEmpty()) {
            Text(text = message)
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}