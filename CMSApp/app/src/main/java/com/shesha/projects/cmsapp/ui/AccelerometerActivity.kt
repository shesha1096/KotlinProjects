package com.shesha.projects.cmsapp.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class AccelerometerActivity : AppCompatActivity(),SensorEventListener2 {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    private var mAnimatedView: AnimatedView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mAnimatedView = AnimatedView(this)
        //Set our content to a view, not like the traditional setting to a layout
        setContentView(mAnimatedView)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(arg0: Sensor?, arg1: Int) {}
    override fun onFlushCompleted(sensor: Sensor?) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            mAnimatedView!!.onSensorEvent(event)
        }
    }

    class AnimatedView(context: Context?) : View(context) {
        private val mPaint: Paint
        private var x = 0
        private var y = 0
        private var viewWidth = 0
        private var viewHeight = 0
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            viewWidth = w
            viewHeight = h
        }

        fun onSensorEvent(event: SensorEvent) {
            x = x - event.values[0].toInt()
            y = y + event.values[1].toInt()
            //Make sure we do not draw outside the bounds of the view.
            //So the max values we can draw to are the bounds + the size of the circle
            if (x <= 0 + CIRCLE_RADIUS) {
                x = 0 + CIRCLE_RADIUS
            }
            if (x >= viewWidth - CIRCLE_RADIUS) {
                x = viewWidth - CIRCLE_RADIUS
            }
            if (y <= 0 + CIRCLE_RADIUS) {
                y = 0 + CIRCLE_RADIUS
            }
            if (y >= viewHeight - CIRCLE_RADIUS) {
                y = viewHeight - CIRCLE_RADIUS
            }
        }

        override fun onDraw(canvas: Canvas) {
            canvas.drawCircle(x.toFloat(), y.toFloat(), CIRCLE_RADIUS.toFloat(), mPaint)
            //We need to call invalidate each time, so that the view continuously draws
            invalidate()
        }

        companion object {
            private const val CIRCLE_RADIUS = 25 //pixels
        }

        init {
            mPaint = Paint()
            mPaint.color = Color.BLUE
        }
    }
}