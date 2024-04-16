package com.example.mincal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddEventDialog(
    state: EventState,
    onEvent: (EventEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(EventEvent.HideDialog)
        },
        title = { Text(text = "Add event") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(EventEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = "Name")
                    }
                )
                TextField(
                    value = state.location,
                    onValueChange = {
                        onEvent(EventEvent.SetLocation(it))
                    },
                    placeholder = {
                        Text(text = "Location")
                    }
                )
                Text("Month, Day and Year")

                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val months = listOf(
                        "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"
                    )

                    var expandedMonth by remember { mutableStateOf(false) }
                    var selectedMonth by remember { mutableIntStateOf(state.month) }

// Dropdown for selecting month
                    Box (
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ){
                        Text(
                            text = months[selectedMonth - 1], // Adjust index to match array indices
                            modifier = Modifier
                                .clickable(onClick = { expandedMonth = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedMonth,
                            onDismissRequest = { expandedMonth = false },
                            modifier = Modifier
                                .width(150.dp)
                                .background(Color.White)
                        ) {
                            for ((index, month) in months.withIndex()) {
                                DropdownMenuItem(onClick = {
                                    selectedMonth = index + 1 // Adjust index to match month numbers
                                    onEvent(EventEvent.SetMonth(selectedMonth))
                                    expandedMonth = false
                                }) {
                                    Text(text = month)
                                }
                            }
                        }
                    }

                    var expandedDay by remember { mutableStateOf(false) }
                    var selectedDay by remember { mutableIntStateOf(state.day) }
                    Box (
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ){
                        Text(
                            text = selectedDay.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedDay = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedDay,
                            onDismissRequest = { expandedDay = false },
                            modifier = Modifier
                                .width(200.dp)
                                .background(Color.White)
                        ) {
                            for (day in 1..31) {
                                DropdownMenuItem(onClick = {
                                    selectedDay = day
                                    onEvent(EventEvent.SetDay(day))
                                    expandedDay = false
                                }) {
                                    Text(text = day.toString())
                                }
                            }
                        }
                    }
                    var expandedYear by remember { mutableStateOf(false) }
                    var selectedYear by remember { mutableIntStateOf(state.year) }

                    Box (
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ){
                        Text(
                            text = selectedYear.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedYear = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedYear,
                            onDismissRequest = { expandedYear = false },
                            modifier = Modifier
                                .width(200.dp)
                                .background(Color.White)
                        ) {
                            for (year in 2000..2050) {
                                DropdownMenuItem(onClick = {
                                    selectedYear = year
                                    onEvent(EventEvent.SetYear(year))
                                    expandedYear = false
                                }) {
                                    Text(text = year.toString())
                                }
                            }
                        }
                    }
                }
                Text("Start Time")

                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    var expandedHour by remember { mutableStateOf(false) }
                    var selectedHour by remember { mutableIntStateOf(state.startH) }

                    var expandedMinute by remember { mutableStateOf(false) }
                    var selectedMinute by remember { mutableIntStateOf(state.startM) }

                    // Dropdown for selecting hour
                    Box (
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ){
                        Text(
                            text = selectedHour.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedHour = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedHour,
                            onDismissRequest = { expandedHour = false },
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.White)
                        ) {
                            for (hour in 0..23) {
                                DropdownMenuItem(onClick = {
                                    selectedHour = hour
                                    onEvent(EventEvent.SetStartH(hour))
                                    expandedHour = false
                                }) {
                                    Text(text = hour.toString())
                                }
                            }
                        }
                    }

                    // Dropdown for selecting minute
                    Box (
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ){
                        Text(
                            text = selectedMinute.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedMinute = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedMinute,
                            onDismissRequest = { expandedMinute = false },
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.White)
                        ) {
                            for (minute in 0..59) {
                                DropdownMenuItem(onClick = {
                                    selectedMinute = minute
                                    onEvent(EventEvent.SetStartM(minute))
                                    expandedMinute = false
                                }) {
                                    Text(text = minute.toString())
                                }
                            }
                        }
                    }
                    /*
                    TextField(
                        value = state.startH.toString(),
                        onValueChange = {
                            val intValue = it.toIntOrNull()
                            if (intValue != null) {
                                onEvent(EventEvent.SetStartH(intValue))
                            }
                        },
                        placeholder = {
                            Text(text = "Hour")
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.49f)
                    )
                    TextField(
                        value = "",
                        onValueChange = {
                            val intValue = it.toIntOrNull()
                            if (intValue != null) {
                                onEvent(EventEvent.SetStartM(intValue))
                            }
                        },
                        placeholder = {
                            Text(text = "Minute")
                        }
                    )*/
                }
                Text("End time")
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    var expandedEndHour by remember { mutableStateOf(false) }
                    var selectedEndHour by remember { mutableIntStateOf(state.endH) }

                    var expandedEndMinute by remember { mutableStateOf(false) }
                    var selectedEndMinute by remember { mutableIntStateOf(state.endM) }

// Dropdown for selecting end hour
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ) {
                        Text(
                            text = selectedEndHour.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedEndHour = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedEndHour,
                            onDismissRequest = { expandedEndHour = false },
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.White)
                        ) {
                            for (hour in 0..23) {
                                DropdownMenuItem(onClick = {
                                    selectedEndHour = hour
                                    onEvent(EventEvent.SetEndH(hour))
                                    expandedEndHour = false
                                }) {
                                    Text(text = hour.toString())
                                }
                            }
                        }
                    }

// Dropdown for selecting end minute
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray) // Set background color to gray
                    ) {
                        Text(
                            text = selectedEndMinute.toString(),
                            modifier = Modifier
                                .clickable(onClick = { expandedEndMinute = true })
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedEndMinute,
                            onDismissRequest = { expandedEndMinute = false },
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.White)
                        ) {
                            for (minute in 0..59) {
                                DropdownMenuItem(onClick = {
                                    selectedEndMinute = minute
                                    onEvent(EventEvent.SetEndM(minute))
                                    expandedEndMinute = false
                                }) {
                                    Text(text = minute.toString())
                                }
                            }
                        }
                    }

                }

            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(EventEvent.SaveEvent)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}