package com.drawing.canvas.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.drawing.canvas.R
import kotlinx.android.synthetic.main.detail_activity.*

class CoordinateDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        val bundle = intent.getBundleExtra("data")
        txtDetail.text = " {rectangles ${bundle.getString("details")} }"
    }
}