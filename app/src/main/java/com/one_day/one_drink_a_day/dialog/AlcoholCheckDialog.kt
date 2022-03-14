package com.one_day.one_drink_a_day.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.activity.MainActivity
import com.one_day.one_drink_a_day.databinding.DialogAlcoholCheckBinding


class AlcoholCheckDialog : DialogFragment() {
    private lateinit var binding: DialogAlcoholCheckBinding
    private lateinit var checkSojo : String
    private lateinit var checkBeer : String
    private lateinit var checkEtc  : String
    private lateinit var checkCountArray : Array<String>
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var mainActivity: MainActivity
    private val TAG = "AlcoholCheckDialog"
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAlcoholCheckBinding.inflate(inflater,container,false)
        val view = binding.root

        checkCountArray = resources.getStringArray(R.array.bottleCount)
        spinnerAdapter = ArrayAdapter(mainActivity, R.layout.check_spinner_item, checkCountArray)

        binding.apply {
            checkSojoSpinner.adapter = spinnerAdapter
            checkBeerSpinner.adapter = spinnerAdapter
            checkEtcSpinner.adapter = spinnerAdapter

            isCancelable = false

            nextBtn.setOnClickListener {
                checkSojo = checkSojoSpinner.selectedItem.toString()
                checkBeer = checkBeerSpinner.selectedItem.toString()
                checkEtc = checkEtcSpinner.selectedItem.toString()
                FirebaseDB.alcoholCheckAdd(checkSojo,checkBeer,checkEtc)
                Log.d(TAG,"소주 : $checkSojo 맥주 : $checkBeer 기타 : $checkEtc")
                dialog?.dismiss()
            }



        }
        return view
    }


}