package com.shesha.projects.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        var frameLayout : FrameLayout = findViewById(R.id.fragment_space)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_space,FragmentExample()).commit()
    }
}