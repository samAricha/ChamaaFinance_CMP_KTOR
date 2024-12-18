package com.teka.chamaa_finance.data_layer.repository

import com.teka.chamaa_finance.data_layer.datasource.NoteLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.NoteEntity


class NoteRepositoryImpl(private val repository: NoteLocalDataSource) : NoteRepository {
    override suspend fun getAllNotes(): List<NoteEntity> = repository.getAllNotes()

    override suspend fun createNote(note: NoteEntity) = repository.createNote(note)

    override suspend fun updateNote(note: NoteEntity) = repository.updateNote(note)

    override suspend fun deleteNote(id: Long) = repository.deleteNote(id)

    override suspend fun getNote(id: Long): NoteEntity? = repository.getNote(id)
}