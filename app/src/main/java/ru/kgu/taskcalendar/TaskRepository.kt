package ru.kgu.taskcalendar

import androidx.lifecycle.LiveData

class TaskRepository(private val storage: TaskStorage) {

    val tasksLive: LiveData<List<Task>> = storage.getTasksLive()

    suspend fun saveTask(task: Task) {
        storage.addTask(task)
    }

    suspend fun changeTask(task: Task) {
        storage.editTask(task)
    }

    suspend fun deleteTask(task: Task) {
        storage.removeTask(task)
    }
}