package lt.github.result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import lt.github.result.common.ActivityResult
import lt.github.result.common.FragmentHelper
import lt.github.result.common.Request

/**
 * @date 2021/1/25
 * @author ydxlt
 */
class Results private constructor(private val mFragmentManager: FragmentManager) {

    private val mFragmentHelper = FragmentHelper(mFragmentManager) { ResultFragment() }
    private val mResultFragment by lazy {
        mFragmentHelper.requireResultFragment()
    }

    fun call(clazz: Class<*>, callback: ActivityResultCallback) {
        call(Intent(mResultFragment.activity, clazz), callback)
    }

    fun call(intent: Intent, callback: ActivityResultCallback) {
        call(intent, null, callback)
    }

    fun call(intent: Intent, options: Bundle?, callback: ActivityResultCallback) {
        callInternal(Request(intent, options), callback)
    }

    private fun callInternal(request: Request, callback: ActivityResultCallback) {
        request.requestCode = ResultFragment.SINGLE_REQUEST_CODE
        mResultFragment.setResultCallback(callback)
        mResultFragment.startActivityForResult(request.intent, request.requestCode, request.options)
    }

    companion object {
        fun of(fragmentActivity: FragmentActivity) =
            Results(fragmentActivity.supportFragmentManager)

        fun of(fragment: Fragment) =
            Results(fragment.childFragmentManager)
    }
}

interface ActivityResultCallback {
    fun onResult(activityResult: ActivityResult)
}