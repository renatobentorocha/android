package com.example.jetnote.repository

import com.example.jetnote.data.NoteDatabaseDAO
import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDAO: NoteDatabaseDAO) {
    suspend fun add(note: Note) = noteDatabaseDAO.insert(note)
    suspend fun update(note: Note) = noteDatabaseDAO.update(note)
    suspend fun delete(note: Note) = noteDatabaseDAO.delete(note)
    suspend fun delete() = noteDatabaseDAO.deleteAll()
    suspend fun get(id: String) = noteDatabaseDAO.getNoteById(id)
    fun get() : Flow<List<Note>> = noteDatabaseDAO.getNotes()
        .flowOn(Dispatchers.IO)
        .conflate()
}