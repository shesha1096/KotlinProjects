package com.shesha.projects.kotlinpracticeapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BroadcastReceiverActivity : AppCompatActivity(), MyBroadcastListener {
    lateinit var operationBtn : Button
    lateinit var xInputText : EditText
    lateinit var yInputText : EditText
    lateinit var resultText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        var myBroadcastReceiver : MyBroadcastReceiver = MyBroadcastReceiver()
        registerReceiver(myBroadcastReceiver, IntentFilter(OperationBroadcastReceiver.RESULT_FILTER))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        xInputText = findViewById(R.id.x_input_text)
        yInputText = findViewById(R.id.y_input_text)
        operationBtn = findViewById(R.id.select_operation_btn)
        operationBtn.setOnClickListener {
            var xInput : Double = xInputText.text.toString().toDouble()
            var yInput : Double = yInputText.text.toString().toDouble()
            var intent : Intent = Intent(this@BroadcastReceiverActivity,OperationsActivity::class.java)
            intent.putExtra("x",xInput)
            intent.putExtra("y",yInput)
            startActivity(intent)

        }
        resultText = findViewById(R.id.result_text_view)
    }


    class MyBroadcastReceiver : BroadcastReceiver()
    {
        lateinit var myBroadcastListener: MyBroadcastListener
        override fun onReceive(context: Context?, intent: Intent?) {
            var result : Double = intent?.getDoubleExtra("Result",0.0) ?: return
            myBroadcastListener.setResult(result.toString())

        }

    }

    override fun setResult(result: String) {
        Log.d("RESULT",result.toString())
        resultText.text = result
    }


}

