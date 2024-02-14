package com.taposek322.todoapp.ui

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.taposek322.todoapp.ToDoViewModel

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel,
    context: Context) {

    val tasks by viewModel.tasks.collectAsState()
    LazyColumn(
        modifier = modifier
    ){
        items(items = tasks, key = { it.id }){task->
            SwipeToDelete(
                item = task,
                onDelete = {viewModel.deleteTask(task)},
                context = context,
                modifier = modifier){
                    TaskContainer(toDo = it,modifier = modifier)
            }
        }
    }
}