package lt.github.rxresult2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.subjects.PublishSubject
import lt.github.result.common.ActivityResult

/**
 * @date 2021/1/25
 * @author ydxlt
 */
internal class RxResultFragment : Fragment() {

    private var resultSubject: PublishSubject<ActivityResult>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun obtainSubject() : PublishSubject<ActivityResult> {
        if(resultSubject == null){
            resultSubject = PublishSubject.create()
        }
        return resultSubject!!
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultSubject?.onNext(
            ActivityResult(
                resultCode,
                data
            )
        )
        resultSubject?.onComplete()
        resultSubject = null
    }

    companion object {
        internal const val SINGLE_REQUEST_CODE = 32
    }
}