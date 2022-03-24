package com.one_day.one_drink_a_day

import android.graphics.Bitmap
import android.net.Uri
import android.security.keystore.StrongBoxUnavailableException
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

object SharedObject {
    private const val TAG = "SharedObject"
    var imgStringArray: ArrayList<String> = arrayListOf("0","0","0","0")
    var spinnerCountArray : Array<String> = arrayOf()
    var saveSpinnerCount : ArrayList<String> = arrayListOf("0","0","0","0")
    var date : String? = null

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