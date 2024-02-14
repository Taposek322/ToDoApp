package com.taposek322.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val dataBaseName="todo"

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun todoDao():ToDoDAO
}
