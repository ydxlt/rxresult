package lt.github.rxresult2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import io.reactivex.Observable
import lt.github.result.common.ActivityResult
import lt.github.result.common.FragmentHelper
import lt.github.result.common.Request

/**
 * @date 2021/1/25
 * @author ydxlt
 */
class RxResults private constructor(private val mFragmentManager: FragmentManager) {

    private val mFragmentHelper = FragmentHelper(mFragmentManager) { RxResultFragment() }
    private val mRxResultFragment by lazy {
        mFragmentHelper.requireResultFragment()
    }

    fun just(clazz: Class<*>): Observable<ActivityResult> {
        return just(clazz, null)
    }

    fun just(clazz: Class<*>, options: Bundle?): Observable<ActivityResult> {
        val request = Request(
            Intent(mRxResultFragment.activity, clazz),
            options
        )
        return requestResult(request)
    }

    fun just(intent: Intent): Observable<ActivityResult> {
        return just(intent, null)
    }

    fun just(intent: Intent, options: Bundle?): Observable<ActivityResult> {
        val request = Request(intent, options)
        return requestResult(request)
    }

    fun justOk(clazz: Class<*>): Observable<ActivityResult> = just(clazz)
        .filter { it.isOk() }

    fun justOk(clazz: Class<*>, options: Bundle?): Observable<ActivityResult> = just(clazz, options)
        .filter { it.isOk() }

    fun justOk(intent: Intent): Observable<ActivityResult> = just(intent)
        .filter { it.isOk() }

    fun justOk(intent: Intent, options: Bundle?): Observable<ActivityResult> = just(intent, options)
        .filter { it.isOk() }

    private fun requestResult(request: Request): Observable<ActivityResult> {
        return Observable.just(request)
            .map {
                it.requestCode =
                    RxResultFragment.SINGLE_REQUEST_CODE
            }
            .flatMap {
                mRxResultFragment.startActivityForResult(
                    request.intent,
                    request.requestCode,
                    request.options
                )
                mRxResultFragment.obtainSubject()
            }
    }

    companion object {

        fun of(fragmentActivity: FragmentActivity) =
            RxResults(fragmentActivity.supportFragmentManager)

        fun of(fragment: Fragment) =
            RxResults(fragment.childFragmentManager)
    }
}
