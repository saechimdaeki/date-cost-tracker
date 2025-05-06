package com.saechimdaeki.dateapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saechimdaeki.dateapplication.data.db.CostDao
import com.saechimdaeki.dateapplication.data.model.CostEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CostViewModel(private val dao: CostDao) : ViewModel() {

    val entries = dao.getAllEntries()

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