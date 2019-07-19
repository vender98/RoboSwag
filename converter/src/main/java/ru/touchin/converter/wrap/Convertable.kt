package ru.touchin.converter.wrap

import android.text.TextWatcher
import android.text.method.KeyListener
import androidx.annotation.ColorInt

interface Convertable {

    fun setText(charSequence: CharSequence, placeCursorToTheEnd: Boolean = false, incrementCursorPosition: Boolean = false)

    fun getText(): CharSequence

    fun addTextChangedListener(watcher: TextWatcher)

    fun setKeyListener(keyListener: KeyListener)

    fun isEnabled(): Boolean

    fun setEnabled(enabled: Boolean)

    fun setTextColor(@ColorInt colorInt: Int)

    fun addOnFocusChangedListener(onFocusChangedListener: (Boolean) -> Unit)

    fun isFocused(): Boolean

}
