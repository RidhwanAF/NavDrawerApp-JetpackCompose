package com.raf.navdrawerapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raf.navdrawerapp.data.UpdateData
import com.raf.navdrawerapp.data.updateData

@Composable
fun UpdatesScreen() {
    LazyColumn {
        item {
            updateData.forEach { data ->
                UpdateItem(updateData = data)
            }
        }
    }
}

@Composable
fun UpdateItem(updateData: UpdateData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = updateData.title ?: "-",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = updateData.date ?: "-",
                fontWeight = FontWeight.Light
            )
            Divider(thickness = 1.dp, modifier = Modifier.padding(8.dp))
            Text(
                text = updateData.content ?: "-",
            )
        }
    }
}