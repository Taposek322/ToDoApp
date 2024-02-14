package com.taposek322.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taposek322.todoapp.data.database.ToDo
import com.taposek322.todoapp.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel  @Inject constructor(private val toDoRepository: ToDoRepository) :ViewModel() {

    private val _tasks: MutableStateFlow<List<ToDo>> = MutableStateFlow(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        getTasksFromDB()
    }

    private fun getTasksFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.getData().collect{data->
                    _tasks.update { data }
            }
        }
    }

    fun insertTask(todo:ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.insertData(todo)
        }
    }

    fun deleteTask(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.deleteData(todo)
        }
    }

    fun getSize():Int{
        return tasks.value.size
    }


}