package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.NoteDateSource
import com.example.jetnote.model.Note
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onNoteAdd: (Note) -> Unit,
    onNoteRemove: (Note) -> Unit
) {
    val context = LocalContext.current

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
            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    onNoteAdd(Note(title = title, description = description))

                    title = ""
                    description = ""

                    Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                }
            })
        }

        /* Notes */

        Divider(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Blue)
        )

        LazyColumn {
            items(notes) {
                NoteRow(note = it) {note ->
                    onNoteRemove(note)
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    note: Note,
    modifier: Modifier = Modifier,
    onClick: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color.LightGray,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            /*Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )*/
        }

    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = NoteDateSource().loadNote(),
        onNoteAdd = {},
        onNoteRemove = {}
    )
}