package com.infinitelearning.habits.data

import android.content.Context

interface AppContainer {
    val habitRepository : HabitRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val habitRepository = WorkManagerHabitRepository(context)
}
