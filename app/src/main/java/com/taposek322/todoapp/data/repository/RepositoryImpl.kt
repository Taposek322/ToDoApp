package com.taposek322.todoapp.data.repository

import com.taposek322.todoapp.data.database.ToDo
import com.taposek322.todoapp.data.database.ToDoDatabase
import com.taposek322.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db:ToDoDatabase):ToDoRepository
{
    //Insert in database
    override suspend fun insertData(toDo:ToDo) = db.todoDao().insert(toDo)

    //Delete element from database
    override suspend fun deleteData(toDo: ToDo) = db.todoDao().delete(toDo)

    //Select all elements from database
    override suspend fun getData(): Flow<List<ToDo>> = db.todoDao().getData()
}