package com.infinitelearning.habits.data

import com.infinitelearning.habits.model.Habit

object DataSource {
    val habits = listOf(
        Habit(
            name = "Kotlin",
            description = "Belajar Kotlin untuk pemula",
            schedule = "Senin - Jumat"
        ),
        Habit(
            name = "Java",
            description = "Belajar Java untuk pemula",
            schedule = "Senin - Jumat"
        ),
        Habit(
            name = "Python",
            description = "Belajar Python Advance",
            schedule = "Senin - Jumat"
        ),
        Habit(
            name = "Jetpack Compose",
            description = "Belajar Jectpack Compose untuk Layouting",
            schedule = "Senin - Jumat"
        ),
        Habit(
            name = "UI/UX Design",
            description = "Belajar UI/UX Design",
            schedule = "Senin - Jumat"
        ),
    )
}
