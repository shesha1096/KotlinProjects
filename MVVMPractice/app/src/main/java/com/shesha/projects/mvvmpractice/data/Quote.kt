package com.shesha.projects.mvvmpractice.data

data class Quote(val quoteText : String, val quoteAutor : String) {

    override fun toString(): String {
        return "$quoteText- $quoteAutor"
    }
}