package com.shesha.projects.kotlinpracticeapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class OperationBroadcastReceiver : BroadcastReceiver() {


    companion object {
        final var RESULT_FILTER : String = "RESULT_FILTER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        class OperationBroadcastReceiver : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
                var result : Double = intent.getDoubleExtra("result",0.0)
                //Log.d("RESULT",result.toString())
                var intent : Intent = Intent()
                intent.putExtra("Result",result.toString())
                intent.setAction(RESULT_FILTER)
                context.sendBroadcast(intent)
            }
        }
    }
}