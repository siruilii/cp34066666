package com.example.cp34066666;



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var bookListView: ListView
    private lateinit var bookAdapter: BookAdapter
    private val books = mutableListOf<Book>()
    private val history = mutableListOf<Book>()

    private lateinit var fanyi: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fanyi = findViewById(R.id.fanyi)
        fanyi.setOnClickListener {
            val intent1 = Intent(this, TranslateActivity::class.java)
            startActivity(intent1)
        }













        // Initialize books
        books.add(Book(R.drawable.image0, "Android from Beginner to Master", "Very well written."))
        books.add(Book(R.drawable.image02, "C++ Programming Language", "Excellent writing."))
        books.add(Book(R.drawable.image03, "Project-Based Introduction to Programming Paperback", "Python Crash Course, 3rd Edition: A Hands-On, Project-Based Introduction to Programming Paperback"))

        bookListView = findViewById(R.id.bookListView)
        bookAdapter = BookAdapter(this, books)
        bookListView.adapter = bookAdapter

        // Set item click listener
        bookListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedBook = books[position]
            history.add(selectedBook) // Add to history
            showBookDetails(selectedBook)
        }

        // History button click listener
        findViewById<View>(R.id.historyButton).setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("history", history.toTypedArray())
            startActivity(intent)
        }

        findViewById<View>(R.id.zhishi).setOnClickListener {
            val intent = Intent(this, ZhishiActivity::class.java)
            startActivity(intent)
        }








    }

    private fun showBookDetails(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("book", book)
        startActivity(intent)
    }
}