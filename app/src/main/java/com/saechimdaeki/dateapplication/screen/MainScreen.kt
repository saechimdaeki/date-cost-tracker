package com.saechimdaeki.dateapplication.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saechimdaeki.dateapplication.presentation.viewmodel.CostViewModel

@Composable
fun MainScreen(viewModel: CostViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var category by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    val entriesByDate by viewModel.dailyTotals.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        CalendarScreen(entriesPerDate = entriesByDate) { selectedDate ->
            Toast.makeText(context, "${selectedDate} 선택됨", Toast.LENGTH_SHORT).show()
            // 나중에 해당 날짜에 기록도 보여줄 수 있음
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                // TODO: 날짜와 함께 저장 로직 연결 예정
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