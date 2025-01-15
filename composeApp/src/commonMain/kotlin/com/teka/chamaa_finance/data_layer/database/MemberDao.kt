package com.teka.chamaa_finance.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teka.chamaa_finance.data_layer.entities.MemberEntity

@Dao
interface MemberDao {
    @Query("SELECT * FROM members")
    suspend fun getAllMembers(): List<MemberEntity>

    @Update
    suspend fun updateMember(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun createMember(member: MemberEntity)

    @Query("DELETE FROM members WHERE id = :memberId")
    suspend fun deleteMemberById(memberId: Long)

    @Query("SELECT * FROM members WHERE id = :memberId")
    suspend fun getMemberById(memberId: Long): MemberEntity?
}
