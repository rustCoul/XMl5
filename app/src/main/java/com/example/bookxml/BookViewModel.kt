package com.example.bookxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    private val _books = MutableLiveData<List<Book>>(listOf(
        Book("1984", "Джордж Оруэлл", "Антиутопический роман..."),
        Book("Мастер и Маргарита", "Михаил Булгаков", "Роман о дьяволе...")
    ))
    val books: LiveData<List<Book>> = _books

    private val _currentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book> = _currentBook

    fun selectBook(book: Book) {
        _currentBook.value = book
    }

    fun updateBook(book: Book) {
        val currentBooks = _books.value?.toMutableList() ?: mutableListOf()
        val index = currentBooks.indexOfFirst { it.title == book.title }
        if (index != -1) {
            currentBooks[index] = book
            _books.value = currentBooks
            _currentBook.value = book
        }
    }
}