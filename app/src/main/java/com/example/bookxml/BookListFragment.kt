package com.example.bookxml

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListFragment : Fragment() {
    interface OnBookSelectedListener {
        fun onBookSelected(book: Book)
    }

    private var listener: OnBookSelectedListener? = null
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bookListView)
        val emptyView = view.findViewById<TextView>(R.id.emptyView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.books.observe(viewLifecycleOwner) { books ->
            val adapter = BookAdapter(books) { book ->
                listener?.onBookSelected(book)
            }
            recyclerView.adapter = adapter

            if (books.isEmpty()) {
                recyclerView.visibility = GONE
                emptyView.visibility = VISIBLE
            } else {
                recyclerView.visibility = VISIBLE
                emptyView.visibility = GONE
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnBookSelectedListener
    }
}