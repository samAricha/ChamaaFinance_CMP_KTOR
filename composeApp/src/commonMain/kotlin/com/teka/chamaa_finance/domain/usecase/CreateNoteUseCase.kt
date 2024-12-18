package com.teka.chamaa_finance.domain.usecase

import com.teka.chamaa_finance.data_layer.entities.NoteEntity
import com.teka.chamaa_finance.data_layer.repository.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateNoteUseCase : KoinComponent {
    private val noteRepository: NoteRepository by inject()
    suspend fun execute(note: NoteEntity) = noteRepository.createNote(note)
}