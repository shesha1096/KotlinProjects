package com.shesha.projects.kotlinpracticeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class OperationsActivity : AppCompatActivity() {
    lateinit var addButton : Button
    lateinit var subtractButton : Button
    lateinit var multiplyButton : Button
    lateinit var divisionButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operations)
        addButton = findViewById(R.id.add_btn)
        subtractButton = findViewById(R.id.subtract_btn)
        multiplyButton = findViewById(R.id.multiply_btn)
        divisionButton = findViewById(R.id.division_btn)
        addButton.setOnClickListener {
            doOperation("Add")
        }
        subtractButton.setOnClickListener {
            doOperation("Subtract")
        }
        multiplyButton.setOnClickListener {
            doOperation("Multiply")
        }
        divisionButton.setOnClickListener {
            doOperation("Division")
        }
    }

    private fun doOperation(operation : String)
    {
        var result : Double = 0.0
        var xInput : Double = intent.getDoubleExtra("x",0.0)
        var yInput : Double = intent.getDoubleExtra("y",0.0)
        when(operation)
        {
            "Add" -> result = xInput + yInput
            "Subtract" -> result = xInput - yInput
            "Multiply" -> result = xInput * yInput
            "Division" -> result = xInput / yInput
            else -> Toast.makeText(this,"Select Proper operation",Toast.LENGTH_LONG).show()
        }
        Log.d("RESULT",result.toString())
        var intent : Intent = Intent(this@OperationsActivity,
            OperationBroadcastReceiver::class.java)
        intent.putExtra("result",result)
        sendBroadcast(intent)

    }
}