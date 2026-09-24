package ru.kgu.taskcalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasks = listOf(
            Task(1, "Отжимания 10 раз", "", "21.09.2026"),
            Task(2, "Бег", "", "21.09.2026"),
            Task(3, "Пресс", "", "22.09.2026")
        )

        val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = TaskAdapter(tasks) { task ->
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("task_id", task.id)
            startActivity(intent)
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}