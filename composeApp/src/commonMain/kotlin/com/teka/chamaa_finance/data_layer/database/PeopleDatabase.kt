package com.teka.chamaa_finance.data_layer.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.data_layer.entities.NoteEntity
import com.teka.chamaa_finance.data_layer.entities.PersonEntity

@Database(
    entities = [PersonEntity::class, NoteEntity::class, ChamaEntity::class, MemberEntity::class, ContributionEntity::class, ChamaAccountEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class PeopleDatabase: RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
    abstract fun noteDao(): NoteDao
    abstract fun groupDao(): GroupDao
    abstract fun memberDao(): MemberDao
    abstract fun contributionDao(): ContributionDao
    abstract fun accountDao(): AccountDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<PeopleDatabase> {
    override fun initialize(): PeopleDatabase
}