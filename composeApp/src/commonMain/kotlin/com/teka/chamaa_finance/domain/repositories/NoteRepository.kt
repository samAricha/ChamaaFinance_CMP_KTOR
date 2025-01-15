package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.NoteEntity

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun createNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(id: Long)
    suspend fun getNote(id: Long): NoteEntity?
}