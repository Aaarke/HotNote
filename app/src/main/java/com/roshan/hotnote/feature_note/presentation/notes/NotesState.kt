package com.roshan.hotnote.feature_note.presentation.notes

import com.roshan.hotnote.feature_note.domain.model.Note
import com.roshan.hotnote.feature_note.domain.util.NotesOrder
import com.roshan.hotnote.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesOrder: NotesOrder = NotesOrder.Date(OrderType.Ascending),
    val isOrderSectionVisible:Boolean =false

)
