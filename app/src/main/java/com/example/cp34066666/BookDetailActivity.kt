package com.example.cp34066666;



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cp34066666.R

class BookDetailActivity : AppCompatActivity() {
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Retrieve the book from intent
        book = intent.getSerializableExtra("book") as Book

        // Display book details
        findViewById<ImageView>(R.id.bookImage).setImageResource(book.imageResId) // 设置图片资源
        findViewById<TextView>(R.id.bookTitle).text = book.title
        findViewById<TextView>(R.id.bookContent).text = book.content
        findViewById<TextView>(R.id.bookComments).text = book.comments.joinToString("\n")

        // Set up comment button
        findViewById<Button>(R.id.addCommentButton).setOnClickListener {
            showCommentDialog()
        }
    }

    private fun showCommentDialog() {
        val input = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Add Comment")
            .setView(input)
            .setPositiveButton("Submit") { dialog, which ->
                val commentText = input.text.toString()
                if (commentText.isNotEmpty()) {
                    book.comments.add(Comment(commentText))
                    findViewById<TextView>(R.id.bookComments).text = book.comments.joinToString("\n")
                } else {
                    Toast.makeText(this, "Comment cannot be empty!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}