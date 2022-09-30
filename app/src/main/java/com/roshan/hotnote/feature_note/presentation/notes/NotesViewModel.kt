package com.roshan.hotnote.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshan.hotnote.feature_note.domain.model.Note
import com.roshan.hotnote.feature_note.domain.use_case.NotesUseCases
import com.roshan.hotnote.feature_note.domain.util.NotesOrder
import com.roshan.hotnote.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCases: NotesUseCases) :
    ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNode: Note? = null

    private var getNoteJob:Job?=null


    init {
        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.Order -> {
                if (state.value.notesOrder::class == notesEvent.notesOrder::class && state.value.notesOrder.orderType == notesEvent.notesOrder.orderType) {
                    return
                }
                getNotes(notesEvent.notesOrder)

            }

            is NotesEvent.DeleteNode -> {
                recentlyDeletedNode = notesEvent.note
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(notesEvent.note)
                }

            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNodeUseCase(recentlyDeletedNode ?: return@launch)
                    recentlyDeletedNode = null

                }

            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value =
                    state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
        }
    }

    private fun getNotes(notesOrder: NotesOrder) {
        getNoteJob?.cancel()

        getNoteJob=notesUseCases.getNotesUseCase(notesOrder).onEach { notes ->
            _state.value = state.value.copy(notes = notes, notesOrder = notesOrder)
        }.launchIn(viewModelScope)
    }
}