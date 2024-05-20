package com.infinitelearning.habits.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.infinitelearning.habits.R

class HabitReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val habitName = inputData.getString(nameKey)

        makeHabitReminderNotification(
            applicationContext.resources.getString(R.string.time_to_doing, habitName),
            applicationContext
        )

        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
    }
}
