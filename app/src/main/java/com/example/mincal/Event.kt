package com.example.mincal

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "event_table")
data class Event(

    val name: String,
    val location: String,
    val startM: Int,
    val startH: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val endM: Int,
    val endH: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    )
