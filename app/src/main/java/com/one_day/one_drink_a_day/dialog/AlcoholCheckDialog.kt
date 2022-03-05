package com.one_day.one_drink_a_day.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.one_day.one_drink_a_day.databinding.DialogAlcoholCheckBinding


class AlcoholCheckDialog : DialogFragment() {
    private lateinit var binding: DialogAlcoholCheckBinding
    private lateinit var checkSojo : String
    private lateinit var checkBeer : String
    private lateinit var checkEtc  : String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAlcoholCheckBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.apply {
            checkSojo = checkSojoSpinner.selectedItem.toString()
           // checkBeer = checkBeerSpinner.selectedItem.toString()

        }
        return view
    }
}