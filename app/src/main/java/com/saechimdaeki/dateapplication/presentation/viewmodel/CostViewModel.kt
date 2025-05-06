package com.saechimdaeki.dateapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saechimdaeki.dateapplication.data.db.CostDao
import com.saechimdaeki.dateapplication.data.model.CostEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CostViewModel @Inject constructor(private val dao: CostDao) : ViewModel() {

    // 전체 지출 목록
    val entries: Flow<List<CostEntry>> = dao.getAllEntries()

    val dailyTotals: StateFlow<Map<LocalDate, Int>> = entries
        .map { list ->
            list
                .filter { it.date.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) }
                .groupBy { LocalDate.parse(it.date) }
                .mapValues { (_, entries) -> entries.sumOf { it.amount } }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()

    fun onCategoryChanged(value: String) {
        _category.value = value
    }

    fun onAmountChanged(value: String) {
        _amount.value = value
    }

    fun saveEntry(date: String) {
        viewModelScope.launch {
            val entry = CostEntry(
                category = _category.value,
                amount = _amount.value.toIntOrNull() ?: 0,
                date = date
            )
            dao.insertEntry(entry)
            _category.value = ""
            _amount.value = ""
        }
    }
}