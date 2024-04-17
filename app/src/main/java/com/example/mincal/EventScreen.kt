package com.example.mincal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventScreen(
    state: EventState,
    onEvent: (EventEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(EventEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add event"
                )
            }
        },
    ) { _ ->
        if(state.isAddingEvent) {
            AddEventDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(EventEvent.SortEvents(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(EventEvent.SortEvents(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }

            items(state.events) { event ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        val monthNames = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

                        val monthName = monthNames[event.month - 1] // Subtract 1 because array index starts from 0
                        Text(
                            text = "$monthName ${event.day}, ${event.year}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "${event.startH}:${if (event.startM < 10) "0${event.startM}" else event.startM}-${event.endH}:${if (event.endM < 10) "0${event.endM}" else event.endM} ${event.name}",
                            fontSize = 20.sp
                        )
                        Text(text = event.location, fontSize = 12.sp)
                    }
                    IconButton(onClick = {
                        onEvent(EventEvent.DeleteEvent(event))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete event"
                        )
                    }
                }
            }
        }
    }
}