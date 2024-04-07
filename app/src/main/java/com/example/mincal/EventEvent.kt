package com.example.mincal

sealed interface EventEvent {
    object saveEvent: EventEvent
    data class SetName(val name: String): EventEvent
    data class SetLocation(val location: String): EventEvent
    data class SetStartM(val startM: String): EventEvent
    data class SetStartH(val startH: String): EventEvent
    data class SetEndM(val endM: String): EventEvent
    data class SetEndH(val endH: String): EventEvent
    data class SetMonth(val month: String): EventEvent
    data class SetYear(val year: String): EventEvent
    data class SetDay(val day: String): EventEvent

    object ShowDialogue: EventEvent
    object HideDialogue: EventEvent
    data class SortEvents(val sortType:SortType)
    data class DeleteEvent(val event: Event): EventEvent


}