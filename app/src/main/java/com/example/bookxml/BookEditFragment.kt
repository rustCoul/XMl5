package com.example.bookxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class BookEditFragment : Fragment() {
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEdit = view.findViewById<EditText>(R.id.titleEdit)
        val authorEdit = view.findViewById<EditText>(R.id.authorEdit)
        val descriptionEdit = view.findViewById<EditText>(R.id.descriptionEdit)

        viewModel.currentBook.observe(viewLifecycleOwner) { book ->
            book?.let {
                titleEdit.setText(it.title)
                authorEdit.setText(it.author)
                descriptionEdit.setText(it.description)
            }
        }

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            viewModel.currentBook.value?.let { book ->
                book.title = titleEdit.text.toString()
                book.author = authorEdit.text.toString()
                book.description = descriptionEdit.text.toString()
                viewModel.updateBook(book)
                parentFragmentManager.popBackStack()
            }
        }
    }
}