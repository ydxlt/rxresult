package lt.github.result.common

import android.app.Activity
import android.content.Intent

/**
 * @date 2021/1/25
 * @author ydxlt
 */
data class ActivityResult(
    val resultCode: Int, val data: Intent?
) {
    fun isOk() = resultCode == Activity.RESULT_OK

    fun isCancel() = resultCode == Activity.RESULT_CANCELED
}