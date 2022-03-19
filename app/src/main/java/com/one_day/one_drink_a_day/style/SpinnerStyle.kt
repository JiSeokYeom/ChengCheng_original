package com.one_day.one_drink_a_day.style

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.one_day.one_drink_a_day.R
import kotlin.coroutines.coroutineContext

class SpinnerStyle(val context : Context,val activity: Activity) {
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var countArray: Array<String>
    private var width : Double? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    fun spinnerSet(spinner: Spinner){
        countArray = context.resources.getStringArray(R.array.bottleCount)
        spinnerAdapter = ArrayAdapter(context, R.layout.spinner_item, countArray)

        spinner.apply {
            adapter = spinnerAdapter
            dropDownWidth = 600
            dropDownVerticalOffset = 30
            initWidth()
            dropDownHorizontalOffset = width
            setPopupBackgroundDrawable(context.getDrawable(R.drawable.style_spinner_popup))
        }
    }

    // 화면 가로 길이 구해서 나누기
    private fun initWidth() {
        if (Build.VERSION.SDK_INT >= 30) {
            val display = activity.display
            val size = Point()
            display?.getRealSize(size)
            width = size.x.toDouble() / 4.5
        } else {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display?.getRealSize(size)
            width = size.x.toDouble() / 4.5
        }
    }
}