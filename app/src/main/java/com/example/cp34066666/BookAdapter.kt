package com.example.cp34066666;


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class BookAdapter(
    context: Context,
    private val books: List<Book>
) : ArrayAdapter<Book>(context, 0, books) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.book_item, parent, false)
        val book = books[position]

        // Set book details
        view.findViewById<ImageView>(R.id.bookImage).setImageResource(book.imageResId)
        view.findViewById<TextView>(R.id.bookTitle).text = book.title
        view.findViewById<TextView>(R.id.bookContent).text = book.content

        // Set comments
        val commentsText = book.comments.joinToString("\n")
        view.findViewById<TextView>(R.id.bookComments).text = commentsText

        return view
    }
}