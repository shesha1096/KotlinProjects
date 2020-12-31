package com.shesha.projects.kotlinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var count : Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView : TextView = findViewById(R.id.sampleTextId)
        var button : Button = findViewById(R.id.clickBtn)
        textView.setText("First Kotlin Application")
        button.setOnClickListener{
            count += 1
            textView.setText("You have clicked me $count times!")
        }
        var calculateAge : Button = findViewById(R.id.calculateAgeBtn)
        calculateAge.setOnClickListener{
            var intent = Intent(this@MainActivity,CalculateAgeActivity::class.java)
            startActivity(intent)
        }

        var recyclerView : Button = findViewById(R.id.recyclerViewBtn)
        recyclerView.setOnClickListener {
            var intent : Intent = Intent(this@MainActivity,RecyclerViewActivity::class.java)
            startActivity(intent)

        }

        var fragmentExample : Button = findViewById(R.id.fragment_example_btn)
        fragmentExample.setOnClickListener {
            var intent : Intent = Intent(this@MainActivity,FragmentsActivity::class.java)
            startActivity(intent)
        }

        var apiCallButton : Button = findViewById(R.id.api_calls_btn)
        apiCallButton.setOnClickListener {
            var intent : Intent = Intent(this@MainActivity,ApiCallsActivity::class.java)
            startActivity(intent)
        }

        var volleyButton : Button = findViewById(R.id.volley_example)
        volleyButton.setOnClickListener {
            var intent : Intent = Intent(this@MainActivity,RetrofitActivity::class.java)
            startActivity(intent)
        }
    }
}