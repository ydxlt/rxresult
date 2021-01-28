package lt.github.result.common

import android.content.Intent
import android.os.Bundle
import androidx.annotation.RestrictTo

/**
 * @date 2021/1/25
 * @author ydxlt
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
data class Request (
    val intent: Intent,
    val options: Bundle? = null
){
    var requestCode: Int = 0
}