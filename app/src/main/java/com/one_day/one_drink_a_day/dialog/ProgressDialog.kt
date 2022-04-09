package com.one_day.one_drink_a_day.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.ybq.android.spinkit.style.WanderingCubes
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ProgressDialogBinding
import com.one_day.one_drink_a_day.firebase.FirebaseDB


class ProgressDialog : DialogFragment() {
    private lateinit var binding: ProgressDialogBinding
    private val TAG = "ProgressDialog"
    private val wanderingCubes  = WanderingCubes()
    private val myRef = FirebaseDB.database.child("publicList")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProgressDialogBinding.inflate(inflater,container,false)

        binding.spinKit.setIndeterminateDrawable(wanderingCubes)

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item : DataSnapshot in snapshot.children){
                    Log.d(TAG,"안에 아이템 확인 $item")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        loadingProgress(3000)

        return binding.root
    }

    private fun loadingProgress(delay : Long){
        Handler().postDelayed({
            dialog?.dismiss()
        }, delay)
    }
}