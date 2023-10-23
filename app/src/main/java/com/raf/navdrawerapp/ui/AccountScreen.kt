package com.raf.navdrawerapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raf.navdrawerapp.R
import com.raf.navdrawerapp.data.AccountData

@Composable
fun AccountScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val accountData = AccountData()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getDrawable(LocalContext.current, accountData.profilePicture))
                .crossfade(true)
                .build(),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_account_circle),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.fillMaxSize()) {
            Text(
                text = accountData.fullName,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "(${accountData.username})",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = accountData.occupation.uppercase(),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.account_details),
                textAlign = TextAlign.Center,
            )
            Divider(thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.email, ": ${accountData.email}"),
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.phone_number, ": ${accountData.phoneNumber}"),
            )
            Text(
                text = stringResource(R.string.date_of_birth, ": ${accountData.dateOfBirth}"),
            )
        }
    }

}