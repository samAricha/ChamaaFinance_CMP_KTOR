package com.teka.chamaa_finance.data_layer.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.teka.chamaa_finance.dtos.ContributionWithNamesDTO

data class ContributionWithMemberName(
    @Embedded val contributionEntity: ContributionEntity,
    @ColumnInfo(name = "memberFirstName") val firstName: String,
    @ColumnInfo(name = "memberLastName") val lastName: String
)





fun ContributionWithMemberName.toContributionWithNameDto(): ContributionWithNamesDTO {
    return ContributionWithNamesDTO(
        contributionId = this.contributionEntity.contributionId,
        memberId = this.contributionEntity.memberId,
        chamaAccountId = this.contributionEntity.chamaAccountId,
        contributionDate = this.contributionEntity.contributionDate,
        contributionAmount = this.contributionEntity.contributionAmount,
        firstName = this.firstName,
        lastName = this.lastName
    )
}

