package com.teka.chamaa_finance.domain.usecase

import com.teka.chamaa_finance.domain.repositories.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNoteUseCase : KoinComponent {
    private val noteRepository: NoteRepository by inject()
    suspend fun execute(id: Long) = noteRepository.getNote(id)
}