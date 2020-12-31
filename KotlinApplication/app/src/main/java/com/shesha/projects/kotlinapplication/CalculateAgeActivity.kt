package com.shesha.projects.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class CalculateAgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_age)
        var ageInput : EditText = findViewById(R.id.calculateEditText)
        var button : Button = findViewById(R.id.calculateBtn)
        var textView : TextView = findViewById(R.id.ageTextView)
        button.setOnClickListener {
            var age : Long = ageInput.text.toString().toLong() * 365 * 24 * 60
            textView.setText("Your age in minutes is: $age")
        }
    }
}