package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teka.chamaa_finance.dtos.ContributionDTO
import java.util.UUID

@Entity(tableName = "contributions")
data class ContributionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val contributionId: String = generateUniqueId(),
    val memberId: String,
    val chamaAccountId: String,
    val contributionDate: String,
    val contributionAmount: String,
    var isBackedUp: Boolean = false
){
    companion object {
        fun generateUniqueId(): String {
            return UUID.randomUUID().toString()
        }
    }
}


fun ContributionEntity.toContributionDto(): ContributionDTO {
    return ContributionDTO(
        memberId = this.memberId,
        chamaAccountId = this.chamaAccountId,
        contributionDate = this.contributionDate,
        contributionAmount = this.contributionAmount,
    )
}