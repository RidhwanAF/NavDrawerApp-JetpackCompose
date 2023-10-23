package com.raf.navdrawerapp.ui

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raf.navdrawerapp.R
import com.raf.navdrawerapp.data.DataStore
import kotlinx.coroutines.launch

@Composable
fun SettingScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStore()
    val darkThemeSwitchState by dataStore.getDarkTheme(context).collectAsState(initial = false)
    val materialUSwitchState by dataStore.getDynamicColorTheme(context)
        .collectAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.dark_theme))
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = darkThemeSwitchState,
                onCheckedChange = {
                    scope.launch {
                        dataStore.saveDarkTheme(context, it)
                    }
                }
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "${stringResource(R.string.dynamicColor)} (Android 12+)")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = materialUSwitchState,
                    onCheckedChange = {
                        scope.launch {
                            dataStore.saveDynamicColorTheme(context, it)
                        }
                    }
                )
            }
        }
    }
}