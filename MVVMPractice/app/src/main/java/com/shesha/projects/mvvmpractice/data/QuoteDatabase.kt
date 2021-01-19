package com.shesha.projects.mvvmpractice.data

class QuoteDatabase private constructor(){

    var quoteDao = QuoteDao()
        private set

    companion object
    {
        @Volatile private var instance : QuoteDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: QuoteDatabase().also { instance = it }
            }
    }
}