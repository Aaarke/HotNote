package com.roshan.hotnote.feature_note.presentation.notes

import com.roshan.hotnote.feature_note.domain.model.Note
import com.roshan.hotnote.feature_note.domain.util.NotesOrder

sealed class NotesEvent{
    data class Order(val notesOrder: NotesOrder):NotesEvent()
    data class DeleteNode(val note: Note):NotesEvent()
    object RestoreNote:NotesEvent()
    object ToggleOrderSection:NotesEvent()
}
