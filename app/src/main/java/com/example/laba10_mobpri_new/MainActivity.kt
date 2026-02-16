package com.example.laba10_mobpri_new

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.laba10_mobpri_new.ui.theme.Laba10_MobPri_NewTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.laba10_mobpri_new.activity.CoroutinesScreen
import com.example.laba10_mobpri_new.activity.ErrorHandlingScreen
import com.example.laba10_mobpri_new.activity.FlowScreen
import com.example.laba10_mobpri_new.activity.SharedFlowScreen
import com.example.laba10_mobpri_new.activity.StateFlowScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laba10_MobPri_NewTheme {
                TaskApp()
            }
        }
    }
}

//навигационная панель, как делали раньше
@Composable
fun TaskApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currEntry by navController.currentBackStackEntryAsState()
                val currScreen = currEntry?.destination?.route
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "1") },
                    label = { Text("1 часть") },
                    selected = currScreen == "1",
                    onClick = { navController.navigate("1") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "2") },
                    label = { Text("2 часть") },
                    selected = currScreen == "2",
                    onClick = { navController.navigate("2") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "3") },
                    label = { Text("3 часть") },
                    selected = currScreen == "3",
                    onClick = { navController.navigate("3") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "4") },
                    label = { Text("4 часть") },
                    selected = currScreen == "4",
                    onClick = { navController.navigate("4") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "5") },
                    label = { Text("5 часть") },
                    selected = currScreen == "5",
                    onClick = { navController.navigate("5") }
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "1",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("1") { CoroutinesScreen() }
            composable("2") { FlowScreen() }
            composable("3") { StateFlowScreen() }
            composable("4") { SharedFlowScreen() }
            composable("5") { ErrorHandlingScreen() }
        }
    }
}
