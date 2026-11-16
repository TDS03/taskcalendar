package ru.kgu.taskcalendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.rvTasks)
        val emptyText = findViewById<TextView>(R.id.tvEmpty)
        val addButton = findViewById<FloatingActionButton>(R.id.fabAdd)

        val taskAdapter = TaskAdapter { task ->
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("task_title", task.title)
            intent.putExtra("task_date", task.date)
            intent.putExtra("task_is_done", task.isDone)
            startActivity(intent)
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = taskAdapter

        taskViewModel.tasks.observe(this) { list ->
            taskAdapter.updateList(list)
            emptyText.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        addButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        addButton.setOnLongClickListener {
            taskViewModel.addTask(Task(title = "Проверка", description = "", date = "01.11.2026"))
            true
        }
    }
}