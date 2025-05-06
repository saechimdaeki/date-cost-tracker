package com.saechimdaeki.datecosttracker

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var category by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("항목 (예: 식사, 교통)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("금액") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Toast.makeText(context, "저장 완료!", Toast.LENGTH_SHORT).show()
                category = ""
                amount = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장")
        }
    }
}