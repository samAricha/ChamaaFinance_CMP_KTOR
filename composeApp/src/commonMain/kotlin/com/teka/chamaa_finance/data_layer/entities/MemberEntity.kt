package teka.android.chamayetu.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
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