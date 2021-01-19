package com.shesha.projects.mvvmpractice.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shesha.projects.mvvmpractice.R
import com.shesha.projects.mvvmpractice.data.Quote
import com.shesha.projects.mvvmpractice.utils.InjectorUtils
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    private fun initializeUI() {
        val factory = InjectorUtils.provideQuoteViewModelFatory()
        val viewModel = ViewModelProviders.of(this,factory).get(QuoteViewModel::class.java)
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote \n\n")
            }
            var textView : TextView = findViewById(R.id.textView_quotes)
            textView.text =  stringBuilder.toString()
        })

        var button : Button = findViewById(R.id.button_add_quote)
        var quoteText : TextView = findViewById(R.id.editText_quote)
        var authorText : TextView = findViewById(R.id.editText_author)
        button.setOnClickListener {
            val quote : Quote = Quote(quoteText.text.toString(),authorText.text.toString())
            viewModel.addQuote(quote)
            quoteText.text = ""
            authorText.text = ""
        }
    }

}