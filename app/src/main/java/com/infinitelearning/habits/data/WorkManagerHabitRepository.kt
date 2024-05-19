package com.infinitelearning.habits.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.infinitelearning.habits.model.Habit
import com.infinitelearning.habits.worker.HabitReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerHabitRepository(context: Context) : HabitRepository {
    private val workManager = WorkManager.getInstance(context)

    override val habits: List<Habit>
        get() = DataSource.habits

    override fun scheduleReminder(duration: Long, unit: TimeUnit, habitName: String) {
        val data = Data.Builder()
        data.putString(HabitReminderWorker.nameKey, habitName)

        val workRequestBuilder = OneTimeWorkRequestBuilder<HabitReminderWorker>()
            .setInitialDelay(duration, unit)
            .setInputData(data.build())
            .build()

        workManager.enqueueUniqueWork(
            habitName + duration,
            ExistingWorkPolicy.REPLACE,
            workRequestBuilder
        )
    }
}
