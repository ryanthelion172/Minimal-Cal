package com.example.mincal

sealed interface EventEvent {
    object SaveEvent: EventEvent
    data class SetName(val name: String): EventEvent
    data class SetLocation(val location: String): EventEvent
    data class SetStartM(val startM: Int): EventEvent
    data class SetStartH(val startH: Int): EventEvent
    data class SetEndM(val endM: Int): EventEvent
    data class SetEndH(val endH: Int): EventEvent
    data class SetMonth(val month: Int): EventEvent
    data class SetYear(val year: Int): EventEvent
    data class SetDay(val day: Int): EventEvent

    object ShowDialog: EventEvent
    object HideDialog: EventEvent
    data class SortEvents(val sortType:SortType): EventEvent
    data class DeleteEvent(val event: Event): EventEvent


}