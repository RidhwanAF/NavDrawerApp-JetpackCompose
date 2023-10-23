package com.raf.navdrawerapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raf.navdrawerapp.R
import com.raf.navdrawerapp.data.AccountData

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val fullName = AccountData().fullName
        Column {
            Text(
                text = "${stringResource(R.string.hello)},",
                fontSize = 32.sp
            )
            Text(
                text = fullName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}