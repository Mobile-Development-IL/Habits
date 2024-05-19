/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.infinitelearning.habits.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.infinitelearning.habits.HabitsApplication
import com.infinitelearning.habits.data.Reminder
import com.infinitelearning.habits.data.HabitRepository

class HabitViewModel(private val habitRepository: HabitRepository) : ViewModel() {

    internal val plants = habitRepository.habits

    fun scheduleReminder(reminder: Reminder) {
        habitRepository.scheduleReminder(reminder.duration, reminder.unit, reminder.plantName)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val habitRepository =
                    (this[APPLICATION_KEY] as HabitsApplication).container.habitRepository
                HabitViewModel(
                    habitRepository = habitRepository
                )
            }
        }
    }
}
