package ru.kgu.taskcalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTasks = findViewById(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(this)

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {
        val db = AppDatabase.getInstance(this)
        lifecycleScope.launch {
            val tasks = db.taskStorage().fetchAllTasks()
            rvTasks.adapter = TaskAdapter(tasks) { task ->
                val intent = Intent(this@MainActivity, TaskDetailActivity::class.java)
                intent.putExtra("task_title", task.title)
                intent.putExtra("task_date", task.date)
                intent.putExtra("task_is_done", task.isDone)
                startActivity(intent)
            }
        }
    }
}
