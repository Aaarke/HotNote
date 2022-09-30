package com.roshan.hotnote.di

import android.app.Application
import androidx.room.Room
import com.roshan.hotnote.feature_note.data.data_source.NoteDao
import com.roshan.hotnote.feature_note.data.data_source.NoteDataBase
import com.roshan.hotnote.feature_note.data.repository.NoteRepositoryImpl
import com.roshan.hotnote.feature_note.domain.repository.NoteRepository
import com.roshan.hotnote.feature_note.domain.use_case.AddNodeUseCase
import com.roshan.hotnote.feature_note.domain.use_case.DeleteNoteUseCase
import com.roshan.hotnote.feature_note.domain.use_case.GetNotesUseCase
import com.roshan.hotnote.feature_note.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDataBase {
        return Room.databaseBuilder(app, NoteDataBase::class.java, NoteDataBase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(dataBase: NoteDataBase):NoteRepository{
        return NoteRepositoryImpl(dataBase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NotesUseCases{
        return NotesUseCases(getNotesUseCase = GetNotesUseCase(repository), deleteNoteUseCase = DeleteNoteUseCase(repository), addNodeUseCase = AddNodeUseCase(repository))
    }
}

