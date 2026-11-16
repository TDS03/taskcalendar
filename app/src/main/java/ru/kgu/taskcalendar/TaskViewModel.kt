package ru.kgu.taskcalendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val tasks: LiveData<List<Task>>

    init {
        val storage = AppDatabase.getInstance(application).taskStorage()
        repository = TaskRepository(storage)
        tasks = repository.tasksLive
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.saveTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.changeTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}