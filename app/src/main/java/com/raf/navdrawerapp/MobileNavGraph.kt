package com.raf.navdrawerapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raf.navdrawerapp.ui.AccountScreen
import com.raf.navdrawerapp.ui.HomeScreen
import com.raf.navdrawerapp.ui.SettingScreen
import com.raf.navdrawerapp.ui.UpdatesScreen
import com.raf.navdrawerapp.ui.navdrawer.NavDrawerMenu

@Composable
fun MobileNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavDrawerMenu.Home.route,
        modifier.padding(innerPaddingValues).animateContentSize()
    ) {
        composable(NavDrawerMenu.Home.route) {
            HomeScreen()
        }
        composable(NavDrawerMenu.Updates.route) {
            UpdatesScreen()
        }
        composable(NavDrawerMenu.Setting.route) {
            SettingScreen()
        }
        composable(accountRoute) {
            AccountScreen()
        }
    }
}

const val accountRoute = "account_route"