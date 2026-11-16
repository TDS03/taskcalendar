package ru.kgu.taskcalendar

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val title = intent.getStringExtra("task_title") ?: ""
        val date = intent.getStringExtra("task_date") ?: ""
        val isDone = intent.getBooleanExtra("task_is_done", false)

        findViewById<TextView>(R.id.tvDetailTitle).text = title
        findViewById<TextView>(R.id.tvDetailDate).text = "Дата: $date"
        findViewById<TextView>(R.id.tvDetailStatus).text =
            if (isDone) "Статус: выполнено" else "Статус: не выполнено"
    }
}