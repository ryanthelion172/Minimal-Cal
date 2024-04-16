package com.example.mincal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mincal.Event
import com.example.mincal.EventDao
import com.example.mincal.EventEvent
import com.example.mincal.EventState
import com.example.mincal.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val dao: EventDao
): ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "No Events on Calender"
    }
    val text: LiveData<String> = _text
    private val _sortType = MutableStateFlow(SortType.ALL_EVENTS)
    private val _events = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.ALL_EVENTS -> dao.readAllData()
               //SortType.DAY_EVENTS -> dao.readDateData()
                SortType.ALL_NAME -> dao.readNameData()

            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(EventState())
    val state = combine(_state, _sortType, _events) { state, sortType, events ->
        state.copy(
            events = events,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EventState())

    fun onEvent(event: EventEvent) {
        when(event) {
            is EventEvent.DeleteEvent -> {
                viewModelScope.launch {
                    dao.deleteEvent(event.event)
                }
            }
            EventEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingEvent = false
                ) }
            }
            EventEvent.SaveEvent -> {
                val name = state.value.name
                val location = state.value.location
                val startM = state.value.startM
                val startH = state.value.startH
                val day = state.value.day
                val month = state.value.month
                val year = state.value.year
                val endM = state.value.endM
                val endH = state.value.endH

                //if(name.isBlank() || startH == 0 || day == 0|| month == 0|| year == 0|| startM == 0) {
                //    return
                //}

                val event = Event(
                    name = name,
                    location = location,
                    startM = startM,
                    startH = startH,
                    day = day,
                    month = month,
                    year = year,
                    endM = endM,
                    endH = endH
                )
                viewModelScope.launch {
                    dao.addEvent(event)
                }
                _state.update { it.copy(
                    isAddingEvent = false,
                    name = "",
                    location = "",
                    startM = 1,
                    startH = 1,
                    day = 1,
                    month = 1,
                    year = 1,
                    endM = 1,
                    endH = 1
                ) }
            }
            is EventEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is EventEvent.SetLocation -> {
                _state.update { it.copy(
                    location = event.location
                ) }
            }
            is EventEvent.SetDay -> {
                _state.update { it.copy(
                    day = event.day
                ) }
            }
            is EventEvent.SetEndH -> {
                _state.update { it.copy(
                    endH = event.endH
                ) }
            }
            is EventEvent.SetStartH -> {
                _state.update { it.copy(
                    startH = event.startH
                ) }
            }
            is EventEvent.SetEndM -> {
                _state.update { it.copy(
                    endM = event.endM
                ) }
            }
            is EventEvent.SetStartM -> {
                _state.update { it.copy(
                    startM = event.startM
                ) }
            }
            is EventEvent.SetYear -> {
                _state.update { it.copy(
                    year = event.year
                ) }
            }
            is EventEvent.SetMonth -> {
                _state.update { it.copy(
                    month = event.month
                ) }
            }
            EventEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingEvent = true
                ) }
            }
            is EventEvent.SortEvents -> {
                _sortType.value = event.sortType
            }
        }
    }

}

//class HomeViewModel : ViewModel() {

  //  private val _text = MutableLiveData<String>().apply {
  //      value = "No Events on Calender"
  //  }
  //  val text: LiveData<String> = _text
//}