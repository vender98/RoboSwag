package ru.touchin.roboswag.components.navigation_new.keyboard_resizeable

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.LayoutRes
import ru.touchin.roboswag.components.navigation.activities.BaseActivity
import ru.touchin.roboswag.components.navigation.activities.OnBackPressedListener
import ru.touchin.roboswag.components.navigation_new.fragments.BaseFragment
import ru.touchin.roboswag.components.utils.UiUtils

abstract class KeyboardResizeableFragment<TActivity : BaseActivity, TState : Parcelable>(
        @LayoutRes layoutRes: Int
) : BaseFragment<TActivity, TState>(
        layoutRes
) {

    private var keyboardIsVisible: Boolean = false

    private val keyboardHideListener = OnBackPressedListener {
        if (keyboardIsVisible) {
            UiUtils.OfViews.hideSoftInput(activity)
            true
        } else {
            false
        }
    }

    private var isHideKeyboardOnBackEnabled = false

    protected open fun onKeyboardShow(diff: Int = 0) {}

    protected open fun onKeyboardHide() {}

    protected fun hideKeyboardOnBackPressed() {
        isHideKeyboardOnBackEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            view.requestApplyInsets()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isHideKeyboardOnBackEnabled) activity.addOnBackPressedListener(keyboardHideListener)
    }

    override fun onPause() {
        super.onPause()
        if (isHideKeyboardOnBackEnabled) activity.removeOnBackPressedListener(keyboardHideListener)
    }

    override fun onStart() {
        super.onStart()
        activity.keyboardBehaviorDetector?.apply {
            setKeyboardHideListener {
                if (keyboardIsVisible) {
                    onKeyboardHide()
                }
                keyboardIsVisible = false
            }
            setKeyboardShowListener { diff ->
                if (!keyboardIsVisible) {
                    onKeyboardShow(diff)
                }
                keyboardIsVisible = true
            }
            startDetection()
        }
    }

    override fun onStop() {
        super.onStop()
        activity.keyboardBehaviorDetector?.apply {
            removeKeyboardHideListener()
            removeKeyboardShowListener()
            stopDetection()
        }
    }
}
