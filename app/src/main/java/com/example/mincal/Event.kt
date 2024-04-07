package com.example.mincal

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
    val endM: Int,
    val endH: Int,

    )
