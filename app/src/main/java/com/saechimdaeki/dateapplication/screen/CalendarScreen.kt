package com.saechimdaeki.dateapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
@Composable
fun CalendarScreen(
    entriesPerDate: Map<LocalDate, Int>,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val currentDate = LocalDate.now()
    val yearMonth = YearMonth.of(currentDate.year, currentDate.month)
    val firstDayOfMonth = yearMonth.atDay(1)
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    Column(Modifier.padding(16.dp)) {
        Text("${yearMonth.year}년 ${yearMonth.monthValue}월", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach {
                Text(it, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            }
        }

        var dayCounter = 1
        for (week in 0..5) {
            Row(Modifier.fillMaxWidth()) {
                for (dayOfWeek in 0..6) {
                    if (week == 0 && dayOfWeek < firstDayOfWeek || dayCounter > daysInMonth) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        val day = dayCounter
                        val date = yearMonth.atDay(day)
                        val totalSpent = entriesPerDate[date] ?: 0
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            TextButton(onClick = { onDateSelected(date) }) {
                                Text(text = "$day", style = MaterialTheme.typography.bodyMedium)
                            }
                            if (totalSpent != 0) {
                                Text(
                                    text = "-%,d원".format(totalSpent),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Red,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                        dayCounter++
                    }
                }
            }
        }
    }
}