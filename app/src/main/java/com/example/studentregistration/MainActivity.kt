package com.example.studentregistration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.studentregistration.screens.HomeScreen
import com.example.studentregistration.ui.theme.StudentRegistrationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            StudentRegistrationTheme {

                var currentPage by remember {
                    mutableStateOf("home")
                }

                when (currentPage) {

                    "home" -> {

                        HomeScreen(
                            onRegisterClick = {
                                currentPage = "register"
                            },
                            onSearchClick = {
                                currentPage = "search"
                            }
                        )
                    }

                    "register" -> {

                        RegistrationPage(
                            onBackClick = {
                                currentPage = "home"
                            }
                        )
                    }

                    "search" -> {

                        SearchPage(
                            onBackClick = {
                                currentPage = "home"
                            }
                        )
                    }
                }
            }
        }
    }
}