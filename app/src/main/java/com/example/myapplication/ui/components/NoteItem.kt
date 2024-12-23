package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Note


@Composable
fun NoteItem(
    note: Note,
    searchQuery: String,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                if (searchQuery.isNotEmpty()) {
                    HighlightedText(
                        text = note.title,
                        highlight = searchQuery,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    HighlightedText(
                        text = note.content,
                        highlight = searchQuery,
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = note.content,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = note.getFormattedDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete note")
            }
        }
    }
}

@Composable
fun HighlightedText(
    text: String,
    highlight: String,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        var currentIndex = 0
        val lowercaseText = text.lowercase()
        val lowercaseHighlight = highlight.lowercase()

        while (true) {
            val startIndex = lowercaseText.indexOf(lowercaseHighlight, currentIndex)
            if (startIndex == -1) {

                append(text.substring(currentIndex, text.length))
                break
            }

            append(text.substring(currentIndex, startIndex))

            withStyle(SpanStyle(background = MaterialTheme.colorScheme.error)) {
                append(text.substring(startIndex, startIndex + highlight.length))
            }

            currentIndex = startIndex + highlight.length
        }
    }

    Text(
        text = annotatedString,
        style = style,
        modifier = modifier
    )
}
