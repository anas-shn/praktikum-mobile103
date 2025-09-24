package com.example.praktikummobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikummobile.data.model.BookDoc
import com.example.praktikummobile.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _books = MutableLiveData<List<BookDoc>>()

    val books: LiveData<List<BookDoc>> =_books

    fun fetchBooks(query: String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks(query,10)
                if (response.isSuccessful){
                    val result = response.body()?.docs ?: emptyList()
                    _books.value =result
                    Log.d("Success_GET_DATA","$result")
                }
            }catch (e: Exception){
                Log.e("API_EXCEPTION", e.localizedMessage ?: "Unknown error")
            }
        }
    }
}