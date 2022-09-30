package com.roshan.hotnote.feature_note.domain.use_case

import com.roshan.hotnote.feature_note.domain.model.Note
import com.roshan.hotnote.feature_note.domain.repository.NoteRepository
import com.roshan.hotnote.feature_note.domain.util.NotesOrder
import com.roshan.hotnote.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository: NoteRepository) {

    operator fun invoke(notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (notesOrder.orderType) {
                is OrderType.Ascending -> {
                    when (notesOrder) {
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase() }

                        is NotesOrder.Date -> notes.sortedBy { it.timeStamp }

                        is NotesOrder.Color -> notes.sortedBy { it.color }


                    }

                }

                is OrderType.Descending -> {
                    when (notesOrder) {
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase() }

                        is NotesOrder.Date -> notes.sortedBy { it.timeStamp }

                        is NotesOrder.Color -> notes.sortedBy { it.color }
                    }
                }

            }
        }

    }
}