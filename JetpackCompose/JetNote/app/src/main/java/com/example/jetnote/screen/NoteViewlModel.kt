package com.example.jetnote.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NoteDateSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewlModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val notesFlow = MutableStateFlow<List<Note>>(emptyList())
    val notes = notesFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.get().collect {
                if(it.isNullOrEmpty()) {
                    Log.d("Empty", "Empty list")
                } else {
                    notesFlow.value = it
                }
            }
        }
        // notes.addAll(NoteDateSource().loadNote())
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.add(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.update(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.delete(note) }
}