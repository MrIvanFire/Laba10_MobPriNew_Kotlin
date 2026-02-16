package com.example.laba10_mobpri_new.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//Часть 1: Базовые корутины

    suspend fun simulateLongOperation(duration: Long): String {
        delay(duration)
        return "Операция завершена за $duration мс"
    }

    suspend fun calculateSum(numbers: List<Int>): Int {
        return withContext(Dispatchers.Default) {
        delay(1000)
            numbers.sum()
        }
    }
    @Composable
    fun CoroutinesScreen() {
        var isLoading by remember { mutableStateOf(false) }
        var result by remember { mutableStateOf<String?>(null) }
        val scope = rememberCoroutineScope()
        Column {
            if (isLoading) {
                CircularProgressIndicator()
            }
            result?.let {
                Text(text = it)
            }
            Button(
                onClick = {
                    isLoading = true
                    result = null
                    scope.launch {
                        val res = simulateLongOperation(2000)
                        result = res
                        isLoading = false
                    }
                },
                enabled = !isLoading
            ) {
                Text("Запустить долгую операцию")
            }

            Button(
                onClick = {
                    isLoading = true
                    result = null
                    scope.launch {
                        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        val sum = calculateSum(numbers)
                        result = "Сумма чисел: $sum"
                        isLoading = false
                    }
                },
                enabled = !isLoading
            ) {
                Text("Вычислить сумму")
            }
        }
    }
