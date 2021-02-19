package com.shesha.projects.cmsapp.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.shapes.Shape
import android.util.Log

class Rectangle(var left: Float, var top: Float, var right: Float, var bottom: Float,var color : Int) : Shape() {

    init {

    }

    override fun draw(canvas: Canvas?, paint: Paint?) {
        Log.d("DRAW","DRAW")
    }
}