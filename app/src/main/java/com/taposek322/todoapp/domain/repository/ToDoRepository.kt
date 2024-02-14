package com.taposek322.todoapp.domain.repository

import com.taposek322.todoapp.data.database.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insertData(toDo:ToDo)
    suspend fun deleteData(toDo: ToDo)
    suspend fun getData(): Flow<List<ToDo>>
}