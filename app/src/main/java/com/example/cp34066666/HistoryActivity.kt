package com.example.cp34066666;



import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val historyListView: ListView = findViewById(R.id.historyListView)
        val history = intent.getSerializableExtra("history") as? List<Book> ?: emptyList()

        val historyAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, history.map { it.title })
        historyListView.adapter = historyAdapter
    }
}