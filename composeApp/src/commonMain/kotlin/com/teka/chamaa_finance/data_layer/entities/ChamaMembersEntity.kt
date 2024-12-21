package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chama_members")
data class ChamaMembersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val memberId: String,
    val chamaId: String,
    var isBackedUp: Boolean = false
)
