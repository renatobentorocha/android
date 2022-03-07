package com.example.jetnote.data

import com.example.jetnote.model.Note

class NoteDateSource {
    fun loadNote(): List<Note> {
        return listOf<Note>(
            Note(title = "A Good Day", description = "We went a vacation..."),
            Note(title = "A Movie Day", description = "Watching a movie with..."),
            Note(title = "A Good Day", description = "We went a vacation..."),
            Note(title = "A Movie Day", description = "Watching a movie with..."),
            Note(title = "A Good Day", description = "We went a vacation..."),
            Note(title = "A Movie Day", description = "Watching a movie with..."),
            Note(title = "A Good Day", description = "We went a vacation..."),
            Note(title = "A Movie Day", description = "Watching a movie with..."),
            Note(title = "A Good Day", description = "We went a vacation..."),
        )
    }
}