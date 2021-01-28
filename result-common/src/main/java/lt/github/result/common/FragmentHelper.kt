package lt.github.result.common

import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * @date 2021/1/25
 * @author ydxlt
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
class FragmentHelper<T : Fragment> (
    private val fragmentManager: FragmentManager,
    private val factory: () -> T
) {

    fun requireResultFragment(): T {
        var resultFragment = findResultFragment()
        if (resultFragment == null) {
            resultFragment = factory()
            fragmentManager.beginTransaction()
                .add(
                    resultFragment,
                    FRAGMENT_TAG
                )
                .commitNowAllowingStateLoss()
        }
        return resultFragment
    }

    private fun findResultFragment(): T? =
        fragmentManager.findFragmentByTag(FRAGMENT_TAG) as? T

    companion object {
        private const val FRAGMENT_TAG = "RxResults.RxResultFragment"
    }
}