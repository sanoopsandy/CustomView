package com.drawing.canvas.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.drawing.canvas.R
import com.drawing.canvas.models.RectCoordinate
import com.drawing.canvas.utils.CustomCanvasView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customCanvasView = customView
        val listOfRectF = customCanvasView.getListOfRectF()
        seekBar.max = 100
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (listOfRectF.size > 0) {
                    val rectCoordinate = listOfRectF.last()
                    scaleRectF(rectCoordinate, progress)
                    customCanvasView.modifyListOfRectF(listOfRectF)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        btnData.setOnClickListener {
            val data = Gson().toJson(customView.getListOfRectF())
            val bundle = Bundle()
            val intent = Intent(this, CoordinateDetailActivity::class.java)
            bundle.putString("details", data.toString())
            intent.putExtra("data", bundle)
            startActivity(intent)
        }
    }

    /*
    *Function to scale the rectangle with respect to seekbar progress
    * @param rect last rectangular coordinate object
    * @param factor progress factor used for scaling
    * */
    private fun scaleRectF(rect: RectCoordinate, factor: Int): RectCoordinate {
        val rectF = rect.rectf
        val scale = CustomCanvasView.BASE_SCALE + (factor * 2)
        rectF.set((rect.dx - scale), (rect.dy - scale), (rect.dx + scale), (rect.dy + scale))
        return rect
    }


}
