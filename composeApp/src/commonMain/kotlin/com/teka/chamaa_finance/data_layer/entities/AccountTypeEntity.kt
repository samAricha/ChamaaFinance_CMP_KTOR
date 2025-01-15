package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_types")
data class AccountTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val accountTypeId: Long = 0,
    val accountName: String,
)
