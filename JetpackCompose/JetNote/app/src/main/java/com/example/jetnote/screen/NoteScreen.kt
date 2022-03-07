package com.example.jetnote.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.model.Note

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onNoteAdd: (Note) -> Unit,
    onNoteRemove: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = { Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notification Icon"
            )}
        )

        /* Content */

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = { title = it },
                modifier = Modifier.padding(top = 10.dp)
            )
            NoteInputText(
                text = description,
                label = "Description",
                onTextChange = { description = it },
                modifier = Modifier.padding(vertical = 10.dp)
            )
            NoteButton(text = "Save", onClick = {})
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = emptyList(),
        onNoteAdd = {},
        onNoteRemove = {}
    )
}