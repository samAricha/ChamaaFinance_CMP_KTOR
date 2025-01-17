package com.teka.chamaa_finance.data_layer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teka.chamaa_finance.dtos.MemberDTO
import java.util.UUID

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val memberId: String = generateUniqueId(),
    val firstName: String,
    val lastName: String,
    val phone: String,
    val dateJoined: String,
    var isBackedUp: Boolean = false
){
    companion object {
        fun generateUniqueId(): String {
            return UUID.randomUUID().toString()
        }
    }
}


fun MemberDTO.toEntity(isBackedUp: Boolean = false): MemberEntity {
    return MemberEntity(
        memberId = this.memberId,
        firstName = this.firstName,
        lastName = this.lastName,
        phone = this.phone,
        dateJoined = this.dateJoined,
        isBackedUp = isBackedUp
    )
}

fun MemberEntity.toDTO(): MemberDTO {
    return MemberDTO(
        memberId = this.memberId,
        firstName = this.firstName,
        lastName = this.lastName,
        phone = this.phone,
        dateJoined = this.dateJoined
    )
}
