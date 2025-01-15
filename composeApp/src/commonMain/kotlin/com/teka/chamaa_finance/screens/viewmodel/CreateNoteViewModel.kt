package com.teka.chamaa_finance.screens.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.entities.NoteEntity
import com.teka.chamaa_finance.domain.usecase.CreateNoteUseCase
import com.teka.chamaa_finance.domain.usecase.GetNoteUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.teka.chamaa_finance.util.getCurrentTimeAsLong




class CreateNoteViewModel : ViewModel(), KoinComponent {

    private val createNoteUseCase: CreateNoteUseCase by inject()
    private val getNoteUseCase: GetNoteUseCase by inject()

    private val _isNoteValid = mutableStateOf(false)
    val isNoteValid = _isNoteValid

    private val _noteId = mutableStateOf(0L)

    private val _title = mutableStateOf("")
    val title = _title

    private val _createdAt = mutableStateOf(0L)

    private val _updatedAt = mutableStateOf(0L)
    val updatedAt = _updatedAt

    private val _body = mutableStateOf("")
    val body = _body

    private val _isNoteSaved = mutableStateOf(false)
    val isNoteSaved = _isNoteSaved

    init {
        getCurrentTime()
    }

    private fun getCurrentTime() {
        val currentTime = getCurrentTimeAsLong()
        _updatedAt.value = currentTime
    }

    fun setTitle(title: String) {
        _title.value = title
        checkNoteValidation()
    }

    fun setBody(body: String) {
        _body.value = body
        checkNoteValidation()
    }

    private fun checkNoteValidation() {
        _isNoteValid.value = _title.value.isEmpty().not() or _body.value.isEmpty().not()
    }

    fun saveNotes(isEditNote: Boolean? = false) = viewModelScope.launch {
        val currentTime = getCurrentTimeAsLong()
        val note = if (isEditNote == true) {
            NoteEntity(
                id = _noteId.value,
                title = _title.value.trim(),
                content = _body.value.trim(),
                createdAt = _createdAt.value,
                updatedAt = currentTime
            )
        } else {
            NoteEntity(
                title = _title.value.trim(),
                content = _body.value.trim(),
                createdAt = currentTime,
                updatedAt = currentTime
            )
        }
        createNoteUseCase.execute(note)
        isNoteSaved.value = true
    }

    fun populateDataFromLocal(id: Long?) = viewModelScope.launch {
        try {
            val note = getNoteUseCase.execute(id ?: 0L)

            _noteId.value = note?.id ?: 0L
            _title.value = note?.title.orEmpty()
            _body.value = note?.content.orEmpty()
            _createdAt.value = note?.createdAt ?: 0L
            _updatedAt.value = note?.updatedAt ?: 0L
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}