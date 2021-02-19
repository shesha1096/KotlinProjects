package com.shesha.projects.cmsapp.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shesha.projects.cmsapp.R


class CustomViewActivity : AppCompatActivity() {
    companion object
    {
        private var colorCode : Int = Color.GREEN
    }

    private lateinit var greenButton : Button
    private lateinit var redButton : Button
    private lateinit var blueButton : Button
    private lateinit var yellowButton : Button
    private lateinit var touchLayout : CustomView
    private var x : Int = 0
    private var y : Int = 0
    private var views : MutableList<View> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

        greenButton = findViewById(R.id.green_btn)
        redButton = findViewById(R.id.red_btn)
        blueButton = findViewById(R.id.blue_btn)
        yellowButton = findViewById(R.id.yellow_btn)
        touchLayout = findViewById(R.id.touch_layout)

        greenButton.setOnClickListener{
            colorCode = Color.GREEN
            touchLayout.setColorCode(colorCode)
        }

        redButton.setOnClickListener{
            colorCode = Color.RED
            touchLayout.setColorCode(colorCode)
        }

        blueButton.setOnClickListener {
            colorCode = Color.BLUE
            touchLayout.setColorCode(colorCode)
        }

        yellowButton.setOnClickListener {
            colorCode = Color.YELLOW
            touchLayout.setColorCode(colorCode)
        }

        touchLayout.setOnTouchListener(handleTouch)

    }


    private val handleTouch = OnTouchListener { v, event ->
        x = event.x.toInt()
        y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> Log.i("TAG", "touched down")
            MotionEvent.ACTION_MOVE -> Log.i("TAG", "moving: ($x, $y)")
            MotionEvent.ACTION_UP -> {
                Log.d("EVENT","UP")
                views.add(DrawView(this))
                touchLayout.addShape(Rectangle(event.x,event.y,event.x+100,event.y+100, colorCode))
                Log.d("EVENT",x.toString())
                Log.d("EVENT",y.toString())

            }
        }
        true
    }

    class DrawView(context: Context?) : View(context) {
        var paint: Paint = Paint()
        override fun onDraw(canvas: Canvas) {
            Log.d("EVENT", colorCode.toString())
            paint.color = colorCode
            canvas.drawRect(30F + x, 30F + y, 80F, 80F, paint)

        }
    }
}