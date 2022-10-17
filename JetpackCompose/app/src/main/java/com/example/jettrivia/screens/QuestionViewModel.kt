package com.example.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.model.QuestionItem
import com.example.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel() {
    private val data: MutableState<DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception
            >> = mutableStateOf(DataOrException())

    init {
        questions()
    }

    private fun questions() {
        viewModelScope.launch {
            try {
                data.value.loading = true
                data.value = repository.questions()
            } finally {
                data.value.loading = false
            }
        }

    }
}