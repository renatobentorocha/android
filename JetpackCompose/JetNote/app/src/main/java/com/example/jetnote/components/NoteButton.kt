package com.example.jetnote.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enable,
        modifier = modifier
    ) {
        Text(text = text)
    }
}