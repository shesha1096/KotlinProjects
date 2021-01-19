package com.shesha.projects.mvvmpractice.utils

import com.shesha.projects.mvvmpractice.data.QuoteDatabase
import com.shesha.projects.mvvmpractice.data.QuoteRepository
import com.shesha.projects.mvvmpractice.ui.auth.QuotesViewModelFactory

object InjectorUtils {
    fun provideQuoteViewModelFatory() :QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(QuoteDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}