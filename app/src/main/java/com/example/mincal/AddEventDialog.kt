package com.example.mincal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TextField(
                        value = state.month.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetMonth(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "Month")
                        }
                    )
                    TextField(
                        value = state.day.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetDay(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "Day")
                        }
                    )
                    TextField(
                        value = state.year.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetYear(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "Year")
                        }
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = state.startH.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetStartH(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "Hour")
                        }
                    )
                    TextField(
                        value = state.startM.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetStartM(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "Minute")
                        }
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = state.endH.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetEndH(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "End Hour")
                        }
                    )
                    TextField(
                        value = state.endM.toString(),
                        onValueChange = {
                            onEvent(EventEvent.SetEndM(it.toInt()))
                        },
                        placeholder = {
                            Text(text = "End Minute")
                        }
                    )
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