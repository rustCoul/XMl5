package com.example.bookxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class BookDetailFragment : Fragment() {
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentBook.observe(viewLifecycleOwner) { book ->
            book?.let {
                view.findViewById<TextView>(R.id.titleText).text = it.title
                view.findViewById<TextView>(R.id.authorText).text = it.author
                view.findViewById<TextView>(R.id.descriptionText).text = it.description
            }
        }

        view.findViewById<Button>(R.id.editButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, BookEditFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}