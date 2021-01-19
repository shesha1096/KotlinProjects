package com.shesha.projects.kotlinpracticeapp

import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class InternetConnectivityActivity : AppCompatActivity() , ConnectivityReceiver.ConnectivityReceiverListener{
    lateinit var cardView: CardView
    lateinit var internetTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connectivity)
        cardView = findViewById(R.id.cardView)
        internetTextView = findViewById(R.id.internet_text_view)
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected)
            run {
                cardView.setCardBackgroundColor(Color.parseColor("#ED2939"))
                internetTextView.text = "No Internet Connection"

            }
        else
            run{
                cardView.setCardBackgroundColor(Color.parseColor("#50C878"))
                internetTextView.text = "You have Internet Connection"
            }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
}