package com.infinitelearning.habits.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infinitelearning.habits.FIVE_SECONDS
import com.infinitelearning.habits.ONE_DAY
import com.infinitelearning.habits.R
import com.infinitelearning.habits.SEVEN_DAYS
import com.infinitelearning.habits.THIRTY_DAYS
import com.infinitelearning.habits.data.Reminder
import com.infinitelearning.habits.model.Habit
import com.infinitelearning.habits.ui.theme.HabitsTheme
import java.util.concurrent.TimeUnit

@Composable
fun HabitsApp(habitViewModel: HabitViewModel = viewModel(factory = HabitViewModel.Factory)) {
    val layoutDirection = LocalLayoutDirection.current
    HabitsTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    start = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateEndPadding(layoutDirection)
                ),
        ) {
            HabitListContent(
                habits = habitViewModel.plants,
                onScheduleReminder = { habitViewModel.scheduleReminder(it) }
            )
        }
    }
}

@Composable
fun HabitListContent(
    habits: List<Habit>,
    onScheduleReminder: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedHabit by rememberSaveable { mutableStateOf(habits[0]) }
    var showReminderDialog by rememberSaveable { mutableStateOf(false) }
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = habits) {
            HabitsListItem(
                habit = it,
                onItemSelect = { habit ->
                    selectedHabit = habit
                    showReminderDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    if (showReminderDialog) {
        ReminderDialogContent(
            onDialogDismiss = { showReminderDialog = false },
            habitName = selectedHabit.name,
            onScheduleReminder = onScheduleReminder
        )
    }
}

@Composable
fun HabitsListItem(habit: Habit, onItemSelect: (Habit) -> Unit, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .clickable { onItemSelect(habit) }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = habit.name,
                modifier = Modifier.fillMaxWidth(),
                style = typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(text = habit.description, style = typography.titleMedium)
            Text(
                text = "${stringResource(R.string.habits)} ${habit.schedule}",
                style = typography.titleMedium
            )
        }
    }
}

@Composable
fun ReminderDialogContent(
    onDialogDismiss: () -> Unit,
    habitName: String,
    onScheduleReminder: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    val reminders = listOf(
        Reminder(R.string.five_seconds, FIVE_SECONDS, TimeUnit.SECONDS, habitName),
        Reminder(R.string.one_day, ONE_DAY, TimeUnit.DAYS, habitName),
        Reminder(R.string.one_week, SEVEN_DAYS, TimeUnit.DAYS, habitName),
        Reminder(R.string.one_month, THIRTY_DAYS, TimeUnit.DAYS, habitName)
    )

    AlertDialog(
        onDismissRequest = { onDialogDismiss() },
        confirmButton = {},
        title = { Text(stringResource(R.string.remind_me, habitName)) },
        text = {
            Column {
                reminders.forEach {
                    Text(
                        text = stringResource(it.durationRes),
                        modifier = Modifier
                            .clickable {
                                onScheduleReminder(it)
                                onDialogDismiss()
                            }
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        },
        modifier = modifier
    )
}
