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
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

object SharedObject {
    private const val TAG = "SharedObject"
    var imgBitmapArray: ArrayList<Bitmap> = arrayListOf()
    var spinnerCountArray : Array<String> = arrayOf()
    var saveSpinnerCount : ArrayList<String> = arrayListOf("0","0","0","0")
    var date : String? = null
    val GALLERY_CODE = 101
    var titleImg : Bitmap? = null
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

}