package com.one_day.one_drink_a_day.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.one_day.one_drink_a_day.activity.Login
import com.one_day.one_drink_a_day.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMypageBinding.inflate(inflater,container,false)

        val login = Intent(activity,Login::class.java)
        binding.googleLogout.setOnClickListener {
            signOut()
            Toast.makeText(activity,"구글 로그아웃 성공", Toast.LENGTH_LONG).show()
            startActivity(login)
            activity?.finish()
        }

        return binding.root
    }

    /// 구글 로그아웃
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

}