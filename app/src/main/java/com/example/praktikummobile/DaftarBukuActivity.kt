package com.example.praktikummobile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktikummobile.data.model.BookDoc
import com.example.praktikummobile.databinding.ActivityDaftarBukuBinding
import com.example.praktikummobile.ui.adapter.BookAdapter
import com.example.praktikummobile.viewmodel.MainViewModel
import com.example.praktikummobile.ui.adapter.OnBookClickListener
import com.example.praktikummobile.ui.fragment.BookDetailFragment

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BookAdapter(emptyList(),this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) {
            adapter.setData(it)
        }
        viewModel.fetchBooks("kotlin")
    }

    override fun onBookClick(book: BookDoc) {
        book.let {b->
            BookDetailFragment(
                title = b.title ?: "-",
                author = b.authorName?.joinToString(", ") ?: "Unknown Author",
                year = "${b.firstPublishYear}",
                coverId = b.coverId ?: 0
            ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
        }
    }

}
