package com.one_day.one_drink_a_day

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

class Permission(val activity : Activity)  {
    val TAG ="Permission"
     fun requirePermissions(permissions: Array<String>, requestCode: Int) : Boolean{
        Log.d(TAG, "권한 요청")
        // isAllPermissionsGranted : 권한이 모두 승인 되었는지 여부 저장
        // all 메서드를 사용하면 배열 속에 들어 있는 모든 값을 체크할 수 있다.
        val isAllPermissionsGranted =
            permissions.all { activity.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
         return if(isAllPermissionsGranted) {
             true
         } else {
             // 사용자에 권한 승인 요청
             ActivityCompat.requestPermissions(activity, permissions, requestCode)
             false
         }
    }
}