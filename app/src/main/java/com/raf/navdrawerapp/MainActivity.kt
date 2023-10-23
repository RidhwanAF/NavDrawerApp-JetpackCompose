package com.raf.navdrawerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raf.navdrawerapp.data.AccountData
import com.raf.navdrawerapp.data.DataStore
import com.raf.navdrawerapp.ui.navdrawer.NavDrawerMenu
import com.raf.navdrawerapp.ui.theme.NavDrawerAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = DataStore()
        setContent {
            val darkTheme =
                dataStore.getDarkTheme(LocalContext.current).collectAsState(initial = false)
            val dynamicColorTheme =
                dataStore.getDynamicColorTheme(LocalContext.current).collectAsState(initial = false)
            NavDrawerAppTheme(
                darkTheme = darkTheme.value,
                dynamicColor = dynamicColorTheme.value
            ) {
                val navDrawerItems = listOf(
                    NavDrawerMenu.Home,
                    NavDrawerMenu.Updates,
                    NavDrawerMenu.Setting,
                )
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val coroutineScope = rememberCoroutineScope()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    AccountData().let { accountData ->
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(getDrawable(accountData.profilePicture))
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = "Profile Picture",
                                            contentScale = ContentScale.Crop,
                                            placeholder = painterResource(R.drawable.ic_account_circle),
                                            modifier = Modifier
                                                .size(64.dp)
                                                .clip(CircleShape)
                                                .border(2.dp, Color.Gray, CircleShape)
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    navController.navigate(accountRoute)
                                                    coroutineScope.launch {
                                                        drawerState.close()
                                                    }
                                                }
                                        ) {
                                            Column {
                                                Text(
                                                    text = accountData.fullName,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 18.sp
                                                )
                                                Text(text = accountData.email)
                                            }
                                            Spacer(modifier = Modifier.weight(1f))
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowRight,
                                                contentDescription = "Profile"
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Divider(thickness = 1.dp)
                                Spacer(modifier = Modifier.height(8.dp))
                                navDrawerItems.forEach { item ->
                                    NavigationDrawerItem(
                                        label = { Text(text = stringResource(item.label)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                            coroutineScope.launch {
                                                drawerState.close()
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (currentDestination?.hierarchy?.any { it.route == item.route } != true) item.icon else item.selectedIcon,
                                                contentDescription = stringResource(item.label)
                                            )
                                        },
                                        badge = {
                                            item.badgeCount?.let {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        },
                                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                    )
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        Scaffold(
                            topBar = {
                                when (currentDestination?.route) {
                                    accountRoute -> {
                                        TopAppBar(
                                            title = {
                                                Text(text = getString(R.string.account))
                                            },
                                            navigationIcon = {
                                                IconButton(onClick = {
                                                    navController.popBackStack()
                                                }) {
                                                    Icon(
                                                        imageVector = Icons.Default.ArrowBack,
                                                        contentDescription = "Navigation Back"
                                                    )
                                                }
                                            }
                                        )
                                    }

                                    else -> {
                                        TopAppBar(
                                            title = {
                                                Text(text = getString(R.string.app_name))
                                            },
                                            navigationIcon = {
                                                IconButton(onClick = {
                                                    coroutineScope.launch {
                                                        drawerState.open()
                                                    }
                                                }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Menu,
                                                        contentDescription = "Menu"
                                                    )
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        ) { innerPaddingValues ->
                            MobileNavGraph(
                                navController = navController,
                                innerPaddingValues = innerPaddingValues
                            )
                        }
                    }
                }
            }
        }
    }
}
