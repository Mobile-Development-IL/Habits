package com.infinitelearning.habits

import android.app.Application
import com.infinitelearning.habits.data.AppContainer
import com.infinitelearning.habits.data.DefaultAppContainer

class HabitsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
