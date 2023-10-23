package com.raf.navdrawerapp.data

import com.raf.navdrawerapp.R

data class AccountData(
    val profilePicture: Int = R.drawable.logo,
    val fullName: String = "John Doe",
    val username: String = "johndoe123",
    val email: String = "johndoe@example.com",
    val phoneNumber: String = "(555) 123-4567",
    val dateOfBirth: String = "January 15, 1985",
    val occupation: String = "Android Developer",
)

data class UpdateData(
    val title: String? = null,
    val content: String? = null,
    val date: String? = null
)

val updateData = listOf(
    UpdateData(
        title = "Added HomeScreen",
        content = "We've introduced a new and user-friendly HomeScreen with exciting features.",
        date = "October 1, 2023"
    ),
    UpdateData(
        title = "Enhanced Security",
        content = "Your data's security is our top priority. We've added new security features.",
        date = "October 5, 2023"
    ),
    UpdateData(
        title = "Improved Performance",
        content = "Our app is now faster and more responsive than ever.",
        date = "October 10, 2023"
    ),
    UpdateData(
        title = "Bug Fixes",
        content = "We've fixed several bugs reported by our users. Enjoy a smoother experience.",
        date = "October 12, 2023"
    ),
    UpdateData(
        title = "Dark Mode Support",
        content = "You can now enable Dark Mode in the app for a more comfortable experience at night.",
        date = "October 15, 2023"
    ),
    UpdateData(
        title = "New Feature: Chat",
        content = "Introducing our new Chat feature for easier communication with friends and colleagues.",
        date = "October 20, 2023"
    ),
    UpdateData(
        title = "Performance Optimization",
        content = "We've optimized the app for better performance on low-end devices.",
        date = "October 22, 2023"
    )
)
