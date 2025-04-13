package com.example.cp34066666;
import java.io.Serializable

data class Book(
    val imageResId: Int,
    val title: String,
    val content: String,
    var comments: MutableList<Comment> = mutableListOf()
) : Serializable

data class Comment(val comment: String) : Serializable