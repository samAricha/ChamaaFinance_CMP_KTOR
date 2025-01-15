package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
