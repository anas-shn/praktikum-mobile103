package com.example.praktikummobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikummobile.data.model.BookDoc
import com.example.praktikummobile.databinding.ListBukuBinding

class BookAdapter(private var books: List<BookDoc>, private val listener: OnBookClickListener) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ListBukuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ListBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.tvTitle.text = book.title ?: "No Title"
        holder.binding.tvAuthor.text = book.authorName?.joinToString() ?: "No Author"
        holder.binding.tvYear.text = book.firstPublishYear?.toString() ?: "No Year"

        holder.itemView.setOnClickListener {
            holder.binding.root.setOnClickListener {
                listener.onBookClick(book)
            }
        }
    }

    fun setData(newBooks: List<BookDoc>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
interface OnBookClickListener {
    fun onBookClick(book: BookDoc)
}