package ru.kgu.taskcalendar

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        // Временный список — тот же, что в MainActivity.
        // На этапе 3 эти данные будут приходить из базы данных Room.
        val tasks = listOf(
            Task(1, "Отжимания 10 раз", "", "21.09.2026"),
            Task(2, "Бег", "", "21.09.2026"),
            Task(3, "Пресс", "", "22.09.2026")
        )

        val taskId = intent.getIntExtra("task_id", -1)
        val task = tasks.find { it.id == taskId }

        if (task != null) {
            findViewById<TextView>(R.id.tvDetailTitle).text = task.title
            findViewById<TextView>(R.id.tvDetailDate).text = "Дата: ${task.date}"
            findViewById<TextView>(R.id.tvDetailStatus).text =
                if (task.isDone) "Статус: выполнено" else "Статус: не выполнено"
        }
    }
}