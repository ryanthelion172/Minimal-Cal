package com.example.mincal

import java.util.Calendar

data class EventState(
    val events: List<Event> = emptyList(),
    val name: String = "",
    val location: String = "",
    val startM: Int = 0,
    val startH: Int = 0,
    val day: Int =  Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    val month: Int =  Calendar.getInstance().get(Calendar.MONTH) + 1,
    val year: Int =  Calendar.getInstance().get(Calendar.YEAR),
    val endM: Int = 0,
    val endH: Int = 0,
    val isAddingEvent:Boolean = false,
    val sortType: SortType = SortType.ALL_EVENTS
)
