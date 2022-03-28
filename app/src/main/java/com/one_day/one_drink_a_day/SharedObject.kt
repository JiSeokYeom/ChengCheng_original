package com.one_day.one_drink_a_day

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.security.keystore.StrongBoxUnavailableException
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import java.io.ByteArrayInputStream
import java.util.*
import kotlin.collections.ArrayList

object SharedObject {
    private const val TAG = "SharedObject"
    var imgStringArray: ArrayList<String> = arrayListOf("0","0","0","0")
    var spinnerCountArray : Array<String> = arrayOf()
    var saveSpinnerCount : ArrayList<String> = arrayListOf("0","0","0","0")
    var date : String? = null
    val GALLERY_CODE = 101
    var titleImg : String? = null
    var titleString : String? = null

    fun spinnerSelect(spinner: Spinner, spinnerCount : Int) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    saveSpinnerCount[spinnerCount] = spinnerCountArray[position]
                    Log.d(TAG, saveSpinnerCount[spinnerCount])

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    //string 을  bitmap 형태로 변환하는 메서드
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToBitmap(data: String?): Bitmap? {
        var bitmap: Bitmap? = null
        val byteArray: ByteArray = Base64.getDecoder().decode(data)
        val stream = ByteArrayInputStream(byteArray)
        bitmap = BitmapFactory.decodeStream(stream)
        return bitmap
    }

}