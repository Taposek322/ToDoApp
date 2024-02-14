package com.taposek322.todoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    val title:String,
    val text:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}


