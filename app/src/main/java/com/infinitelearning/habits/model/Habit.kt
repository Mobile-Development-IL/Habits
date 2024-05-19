package com.infinitelearning.habits.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habit(
    val name: String,
    val description: String,
    val schedule: String
): Parcelable
