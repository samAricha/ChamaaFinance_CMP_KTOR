package com.teka.chamaa_finance.data_layer.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getPeopleDatabase(): PeopleDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "people_room.db")
    return Room.databaseBuilder<PeopleDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}