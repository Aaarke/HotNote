package com.roshan.hotnote.feature_note.presentation.notes.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roshan.hotnote.feature_note.domain.util.NotesOrder
import com.roshan.hotnote.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    onOrderChange: (NotesOrder) -> Unit
) {

    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = notesOrder is NotesOrder.Title,
                onSelect = { onOrderChange(NotesOrder.Title(notesOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        DefaultRadioButton(
            text = "Date",
            selected = notesOrder is NotesOrder.Date,
            onSelect = { onOrderChange(NotesOrder.Date(notesOrder.orderType)) })

        Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
            text = "Color",
            selected = notesOrder is NotesOrder.Color,
            onSelect = { onOrderChange(NotesOrder.Color(notesOrder.orderType)) })
        Spacer(modifier = Modifier.width(8.dp))

        DefaultRadioButton(
            text = "Ascending",
            selected = notesOrder.orderType is OrderType.Ascending,
            onSelect = { onOrderChange(notesOrder.copy(OrderType.Ascending)) })

        Spacer(modifier = Modifier.width(8.dp))

        DefaultRadioButton(
            text = "Descending",
            selected = notesOrder.orderType is OrderType.Descending,
            onSelect = { onOrderChange(notesOrder.copy(OrderType.Descending)) })


    }
}