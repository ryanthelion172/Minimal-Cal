package com.example.mincal.events

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Query("SELECT * FROM event_table ORDER BY year, month, day, startH, startM DESC")
    fun readAllData(): LiveData<List<Event>>
}