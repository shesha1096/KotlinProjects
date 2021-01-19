package com.shesha.projects.mvvmpractice.ui.auth

import androidx.lifecycle.ViewModel
import com.shesha.projects.mvvmpractice.data.Quote
import com.shesha.projects.mvvmpractice.data.QuoteRepository

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote : Quote)
    {
        quoteRepository.addQuote(quote)
    }
}