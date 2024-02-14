package com.taposek322.todoapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {

    //Insert in database
    @Insert
    fun insert(todo: ToDo)

    //Delete from database
    @Delete
    fun delete(todo: ToDo)

    //Select all elements
    @Query("SELECT * FROM todo")
    fun getData(): Flow<List<ToDo>>
}