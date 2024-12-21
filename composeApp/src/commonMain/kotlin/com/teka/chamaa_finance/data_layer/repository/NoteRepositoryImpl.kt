package com.teka.chamaa_finance.data_layer.repository

import com.teka.chamaa_finance.data_layer.datasource.NoteLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.NoteEntity


class NoteRepositoryImpl(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {

    override suspend fun getAllNotes(): List<NoteEntity> = noteLocalDataSource.getAllNotes()

    override suspend fun createNote(note: NoteEntity) = noteLocalDataSource.createNote(note)

    override suspend fun updateNote(note: NoteEntity) = noteLocalDataSource.updateNote(note)

    override suspend fun deleteNote(id: Long) = noteLocalDataSource.deleteNote(id)

    override suspend fun getNote(id: Long): NoteEntity? = noteLocalDataSource.getNote(id)

}