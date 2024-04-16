package com.example.mincal

data class EventState(
    val events: List<Event> = emptyList(),
    val name: String = "",
    val location: String = "",
    val startM: Int = 1,
    val startH: Int = 1,
    val day: Int = 1,
    val month: Int = 1,
    val year: Int = 1,
    val endM: Int = 1,
    val endH: Int = 1,
    val isAddingEvent:Boolean = false,
    val sortType: SortType = SortType.ALL_EVENTS
)
