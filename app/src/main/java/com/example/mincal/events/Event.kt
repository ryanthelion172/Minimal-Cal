package com.example.mincal.events

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val location: String,
    val startM: Int,
    val startH: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val endMi: Int,
    val endH: Int,

    )
