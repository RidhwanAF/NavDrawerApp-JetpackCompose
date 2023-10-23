package com.raf.navdrawerapp.ui.navdrawer

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.raf.navdrawerapp.R
import com.raf.navdrawerapp.data.updateData

sealed class NavDrawerMenu(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val badgeCount: Int? = null
) {
    object Home: NavDrawerMenu(
        "home_route",
        R.string.home,
        Icons.Outlined.Home,
        Icons.Filled.Home
    )
    object Updates: NavDrawerMenu(
        "update_route",
        R.string.updates,
        Icons.Outlined.Info,
        Icons.Filled.Info,
        updateData.size
    )
    object Setting: NavDrawerMenu(
        "setting_route",
        R.string.settings,
        Icons.Outlined.Settings,
        Icons.Filled.Settings,
    )
}
