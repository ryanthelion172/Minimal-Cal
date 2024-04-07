package com.example.mincal.events

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Upsert
import androidx.room.Delete




interface EventDao {

    @Upsert
    suspend fun addEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM event_table ORDER BY year, month, day, startH, startM DESC")
    fun readAllData(): Flow<List<Event>>

    @Query("SELECT * FROM event_table WHERE year = 2024 ORDER BY startH, startM DESC")
    fun readDateData(): Flow<List<Event>>
}