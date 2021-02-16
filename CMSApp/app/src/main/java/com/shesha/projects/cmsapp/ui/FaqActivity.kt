package com.shesha.projects.cmsapp.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.FaqRecyclerAdapter
import com.shesha.projects.cmsapp.model.Faq
import java.util.*

class FaqActivity : AppCompatActivity() {

    private var faqRecyclerView: RecyclerView? = null
    private var faqArrayList: ArrayList<Faq>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        faqArrayList = ArrayList()
        faqRecyclerView = findViewById(R.id.faq_recycler_view)
        initFaqs()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val faqRecyclerAdapter = faqArrayList?.let { FaqRecyclerAdapter(this, it) }
        faqRecyclerView!!.adapter = faqRecyclerAdapter
        faqRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    private fun initFaqs() {
        faqArrayList!!.add(Faq("How do I find a mentor?", "First you’ll need a crystal ball. Then, with great concentration, gaze into the mystic realms. When you are ready, the right mentor will find you."))
        faqArrayList!!.add(Faq("How do I find a mentor?", "First you’ll need a crystal ball. Then, with great concentration, gaze into the mystic realms. When you are ready, the right mentor will find you."))
        faqArrayList!!.add(Faq("How do I find a mentor?", "First you’ll need a crystal ball. Then, with great concentration, gaze into the mystic realms. When you are ready, the right mentor will find you."))
        faqArrayList!!.add(Faq("How do I find a mentor?", "First you’ll need a crystal ball. Then, with great concentration, gaze into the mystic realms. When you are ready, the right mentor will find you."))
    }
}