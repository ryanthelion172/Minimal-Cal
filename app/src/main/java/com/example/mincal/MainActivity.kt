package com.example.mincal

import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Column
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mincal.databinding.ActivityMainBinding
import com.example.mincal.ui.gallery.GalleryViewModel
import com.example.mincal.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            EventDatabase::class.java,
            "events.db"
        ).build()
    }
    private val viewModel by viewModels<GalleryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GalleryViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
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
                            MenuItem(
                                id = "month",
                                title = "Monthly",
                                contentDescription = "Go to Month Screen",
                                icon = Icons.Default.DateRange
                            ),
                        ),
                        onItemClick = {
                            println("Clicked on ${it.title}")
                            when (it.id) {
                                "events" -> navigateToFragment(R.id.nav_home, scaffoldState.drawerState)
                                "daily" -> navigateToFragment(R.id.nav_gallery, scaffoldState.drawerState)
                                "month" -> navigateToFragment(R.id.nav_slideshow, scaffoldState.drawerState)
                            }
                            println("Clicked on ${it.title}")
                        }
                    )
                }
            ) {

            }

            val state by viewModel.state.collectAsState()
            DailyScreen(state = state, onEvent = viewModel::onEvent)
        }
    }

    private fun navigateToFragment(fragmentId: Int, drawerState: DrawerState) {
        // Get the NavController
        println( R.id.nav_host_fragment_content_main)
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Navigate to the specified fragment
        navController.navigate(fragmentId)

        // Close the drawer (optional)
        //drawerState.close()
    }

    /*private fun navigateToFragment(fragmentId: Int) {
        // Get the NavController
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Navigate to the specified fragment
        navController.navigate(fragmentId)

        // Close the drawer (optional)
        // drawerState.close()
    }*/
}

/*class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}*/