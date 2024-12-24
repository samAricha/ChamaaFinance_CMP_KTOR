package com.teka.chamaa_finance.data_layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.data_layer.entities.NoteEntity
import com.teka.chamaa_finance.data_layer.entities.PersonEntity

@Database(
    entities = [PersonEntity::class, NoteEntity::class, ChamaEntity::class],
    version = 1
)
abstract class PeopleDatabase: RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
    abstract fun noteDao(): NoteDao
    abstract fun groupDao(): GroupDao

}