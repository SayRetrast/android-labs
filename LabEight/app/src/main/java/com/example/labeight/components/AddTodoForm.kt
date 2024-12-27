package com.example.labeight.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.labeight.db.Task
import com.example.labeight.db.db

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoForm(contentPadding: PaddingValues) {
    val taskDao = db.taskDao()

    var taskTitle by remember { mutableStateOf("") }
    val priorities = listOf(2, 1, 0)
    val (selectedPriority, onPrioritySelected) = remember { mutableIntStateOf(priorities[1]) }

    Column(modifier = Modifier.padding(contentPadding).fillMaxSize().padding(top = 28.dp)) {
        TextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            placeholder = { Text(text = "Task name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            priorities.forEach { priority ->
                val color = when (priority) {
                    2 -> Color.Red
                    1 -> Color(0xFFFFA500)
                    0 -> Color(0xFFFFC107)
                    else -> Color.Gray
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onPrioritySelected(priority) }
                ) {
                    RadioButton(
                        selected = (priority == selectedPriority),
                        onClick = { onPrioritySelected(priority) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = color,
                            unselectedColor = color
                        )
                    )
                    Text(
                        text =  when (priority) {
                            2 -> "High"
                            1 -> "Medium"
                            0 -> "Low"
                            else -> ""
                        } ,
                        color = color,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button (
            onClick = {
                if (taskTitle.isNotBlank()) {
                    taskDao.insert(Task(task = taskTitle, priority = selectedPriority))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = taskTitle.isNotBlank()
        ) {
            Text(text = "Add Task")
        }
    }
}