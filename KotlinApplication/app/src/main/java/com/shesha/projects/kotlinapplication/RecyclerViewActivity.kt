package com.shesha.projects.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        var movieList = ArrayList<MovieModel>()
        for (i in 0..7)
        {
            var movieModel : MovieModel = MovieModel("The Equalizer","A movie about a retired Army officer.")
            movieList.add(movieModel)
        }
        var recyclerView : RecyclerView = findViewById(R.id.movie_recycler_view)
        var adapter : RecyclerViewAdapter = RecyclerViewAdapter(movieList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}