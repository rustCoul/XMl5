package com.example.bookxml

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), BookListFragment.OnBookSelectedListener {
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, BookListFragment())
                .commit()
        }
    }

    override fun onBookSelected(book: Book) {
        viewModel.selectBook(book)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BookDetailFragment())
            .addToBackStack(null)
            .commit()
    }
}