package com.example.mincal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
//import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import kotlinx.coroutines.launch
import androidx.compose.ui.text.TextStyle

@Composable
fun DailyScreen(
    state: EventState,
    onEvent: (EventEvent) -> Unit
) {
    /*Scaffold(
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
    ) { _ ->*/
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "events",
                        title = "Events",
                        contentDescription = "Go to event screen",
                        icon = Icons.Default.Check
                    ),
                    MenuItem(
                        id = "daily",
                        title = "Daily",
                        contentDescription = "Go to daily screen",
                        icon = Icons.Default.DateRange
                    ),
                    /*MenuItem(
                        id = "month",
                        title = "Monthly",
                        contentDescription = "Go to Month Screen",
                        icon = Icons.Default.DateRange
                    ),*/
                ),
                onItemClick = {
                    when (it.id) {
                        "events" -> navigateToFragment(R.id.nav_home, scaffoldState.drawerState, navController)
                        "daily" -> navigateToFragment(R.id.nav_gallery, scaffoldState.drawerState, navController)
                        //"month" -> navigateToFragment(R.id.nav_slideshow, scaffoldState.drawerState)
                    }
                    println("Clicked on ${it.title}")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(EventEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add event"
                )
            }
        }
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
                    /*modifier = Modifier
                        .fillMaxWidth(),
                        //.horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically*/
                ) {
                    var isExpandedYears by remember { mutableStateOf(false) }
                    var isExpandedMonths by remember { mutableStateOf(false) }
                    var isExpandedDays by remember { mutableStateOf(false) }
                    // Dropdown box for SortMonthsInYear enum
                    Row() {
                        Box(
                            //modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = state.sortMonthsInYear.toMonthName(),
                                modifier = Modifier
                                    .clickable(onClick = { isExpandedMonths = true })
                                    .padding(16.dp)
                                    .background(Color.White), // Set background color to gray
                                style = TextStyle(fontSize = 20.sp) // Adjust the font size as needed

                            )
                            DropdownMenu(
                                expanded = isExpandedMonths,
                                onDismissRequest = { isExpandedMonths = false }
                            ) {
                                SortMonthsInYear.values().forEach { month ->
                                    DropdownMenuItem(onClick = {
                                        state.sortMonthsInYear = month
                                        onEvent(
                                            EventEvent.SortDayEvents(
                                                state.sortDaysInMonth,
                                                state.sortMonthsInYear,
                                                state.sortYears
                                            )
                                        )
                                        isExpandedMonths = false
                                    }) {
                                        val monthName = when (month) {
                                            SortMonthsInYear.MONTH_1 -> "January"
                                            SortMonthsInYear.MONTH_2 -> "February"
                                            SortMonthsInYear.MONTH_3 -> "March"
                                            SortMonthsInYear.MONTH_4 -> "April"
                                            SortMonthsInYear.MONTH_5 -> "May"
                                            SortMonthsInYear.MONTH_6 -> "June"
                                            SortMonthsInYear.MONTH_7 -> "July"
                                            SortMonthsInYear.MONTH_8 -> "August"
                                            SortMonthsInYear.MONTH_9 -> "September"
                                            SortMonthsInYear.MONTH_10 -> "October"
                                            SortMonthsInYear.MONTH_11 -> "November"
                                            SortMonthsInYear.MONTH_12 -> "December"
                                        }
                                        Text(text = monthName)
                                    }
                                }
                            }
                        }
                        Box(
                            //modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = state.sortDaysInMonth.toDayOfMonth().toString(),
                                modifier = Modifier
                                    .clickable(onClick = { isExpandedDays = true })
                                    .padding(16.dp)
                                    .background(Color.White), // Set background color to gray
                                style = TextStyle(fontSize = 20.sp) // Adjust the font size as needed

                            )
                            // Dropdown box for SortDaysInMonth enum
                            DropdownMenu(
                                expanded = isExpandedDays,
                                onDismissRequest = { isExpandedDays = false }
                            ) {
                                SortDaysInMonth.values().forEach { day ->
                                    DropdownMenuItem(onClick = {
                                        state.sortDaysInMonth = day

                                        onEvent(
                                            EventEvent.SortDayEvents(
                                                state.sortDaysInMonth,
                                                state.sortMonthsInYear,
                                                state.sortYears
                                            )
                                        )
                                        isExpandedDays = false
                                    }) {
                                        Text(text = day.name.substringAfter("_")) // Extracts the number
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = state.sortYears.toYearNumber().toString(),
                                modifier = Modifier
                                    .clickable(onClick = { isExpandedYears = true })
                                    .padding(16.dp)
                                    .background(Color.White), // Set background color to gray
                                style = TextStyle(fontSize = 20.sp) // Adjust the font size as needed

                            )
                            // Dropdown box for SortYears enum
                            DropdownMenu(
                                expanded = isExpandedYears,
                                onDismissRequest = { isExpandedYears = false }
                            ) {
                                SortYears.values().forEach { year ->
                                    DropdownMenuItem(onClick = {
                                        state.sortYears = year
                                        onEvent(
                                            EventEvent.SortDayEvents(
                                                state.sortDaysInMonth,
                                                state.sortMonthsInYear,
                                                state.sortYears
                                            )
                                        )

                                        isExpandedYears = false
                                    }) {
                                        Text(text = year.name.substringAfter("_")) // Extracts the number
                                    }
                                }
                            }
                        }
                    }
                    /*SortType.values().forEach { sortType ->
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
                    }*/
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

fun navigateToFragment(fragmentId: Int, drawerState: DrawerState, navController: NavController) {
// Get the NavController
    // Navigate to the specified fragment
    if (navController.currentDestination != null) {

        navController.navigate(fragmentId)
    }
    // Close the drawer (optional)
    //drawerState.close()

}
