package com.example.jetnote.screen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.data.NoteDateSource
import com.example.jetnote.model.Note


class NoteViewlModel : ViewModel() {

    private var notes = mutableStateListOf<Note>()

    init {
        notes.addAll(NoteDateSource().loadNote())
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    fun getNotes(): List<Note> {
        return notes.toList()
    }
}