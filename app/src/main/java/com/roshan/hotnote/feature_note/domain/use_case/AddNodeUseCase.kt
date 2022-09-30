package com.roshan.hotnote.feature_note.domain.use_case

import com.roshan.hotnote.feature_note.domain.model.InValidNoteException
import com.roshan.hotnote.feature_note.domain.model.Note
import com.roshan.hotnote.feature_note.domain.repository.NoteRepository

class AddNodeUseCase(private val repository: NoteRepository) {

    @Throws(InValidNoteException::class)
    suspend  operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InValidNoteException("note title can't be blank")
        }
        if (note.content.isBlank()){
            throw InValidNoteException("note content can't  be blank")
        }
        repository.insertNote(note)
    }
}