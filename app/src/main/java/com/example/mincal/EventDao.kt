package com.example.mincal

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Upsert
import androidx.room.Delete

@Dao
interface EventDao {

    @Upsert
    suspend fun addEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM event_table ORDER BY year, month, day, startH, startM DESC")
    fun readAllData(): Flow<List<Event>>

    @Query("SELECT * FROM event_table WHERE year > :currentYear OR (year = :currentYear AND month > :currentMonth) OR (year = :currentYear AND month = :currentMonth AND day >= :currentDay) ORDER BY year, month, day, startH, startM DESC")
    fun readPresentData(currentYear: Int, currentMonth: Int, currentDay: Int): Flow<List<Event>>

    @Query("SELECT * FROM event_table ORDER BY name ASC")
    fun readNameData(): Flow<List<Event>>

    @Query("SELECT * FROM event_table WHERE year = 2024 ORDER BY startH, startM DESC")
    fun readDateData(): Flow<List<Event>>
}