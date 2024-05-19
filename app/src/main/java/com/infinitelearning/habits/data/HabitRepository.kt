package com.infinitelearning.habits.data

import com.infinitelearning.habits.model.Habit
import java.util.concurrent.TimeUnit

interface HabitRepository {
    fun scheduleReminder(duration: Long, unit: TimeUnit, habitName: String)
    val habits: List<Habit>
}
