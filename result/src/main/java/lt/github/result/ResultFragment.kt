package lt.github.result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import lt.github.result.common.ActivityResult

/**
 * @date 2021/1/25
 * @author ydxlt
 */
internal class ResultFragment : Fragment() {

    private var mResultCallback: ActivityResultCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun setResultCallback(resultCallback: ActivityResultCallback) {
        this.mResultCallback = resultCallback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mResultCallback?.onResult(
            ActivityResult(
                resultCode,
                data
            )
        )
        mResultCallback = null
    }

    companion object {
        internal const val SINGLE_REQUEST_CODE = 32
    }
}

