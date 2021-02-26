package com.shesha.kotlinprojects.daggerexampleapp


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.kotlinprojects.daggerexampleapp.adapters.ArticleRecyclerAdapter
import com.shesha.kotlinprojects.daggerexampleapp.di.MyApplication
import com.shesha.kotlinprojects.daggerexampleapp.network.ApiServiceBuilder
import com.shesha.kotlinprojects.daggerexampleapp.network.PageEndPoints
import com.shesha.kotlinprojects.daggerexampleapp.network.PageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.ArrayList
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (getApplication() as MyApplication).component!!.inject(this)
        recyclerView = findViewById<View>(R.id.pages_recycler_view) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))



//Using retrofit
        val pageEndPoints : PageEndPoints= ApiServiceBuilder().getClient()?.create(PageEndPoints::class.java)
                ?: return
        val call: Call<List<PageResponse?>?>? = pageEndPoints.pages
        call?.enqueue(object : Callback<List<PageResponse?>?> {

            override fun onResponse(call: Call<List<PageResponse?>?>?, response: Response<List<PageResponse?>?>?) {
                val statuscode: Int = response?.code() ?: return
                val matchlist: List<PageResponse> = response.body() as List<PageResponse>
                val articleRecyclerAdapter = ArticleRecyclerAdapter(this@MainActivity, matchlist as ArrayList<PageResponse>)
                recyclerView!!.adapter = articleRecyclerAdapter
            }

            override fun onFailure(call: Call<List<PageResponse?>?>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }
        })
    }
}