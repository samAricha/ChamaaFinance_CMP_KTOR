package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_types")
data class TransactionTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val transactionTypeId: Long = 0,
    val transactionName: String,
)
