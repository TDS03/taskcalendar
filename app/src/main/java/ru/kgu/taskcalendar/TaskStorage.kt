package ru.kgu.taskcalendar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskStorage {

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun editTask(task: Task)

    @Delete
    suspend fun removeTask(task: Task)

    @Query("SELECT * FROM tasks_table ORDER BY date ASC")
    suspend fun fetchAllTasks(): List<Task>

    @Query("SELECT * FROM tasks_table WHERE date = :selectedDate")
    suspend fun fetchTasksForDate(selectedDate: String): List<Task>
}