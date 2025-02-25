package com.dev.anasmohammed.corex.picker.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Entry point for initializing the picker. This object provides methods
 * to create a [PickerMediator] for managing picker operations in either
 * a [FragmentActivity] or a [Fragment].
 */

object CoreXPicker {
    /**
     * Initializes the picker with a [FragmentActivity].
     *
     * @param activity The [FragmentActivity] in which the picker system will operate.
     * @return A [PickerMediator] instance to configure and manage picker operations.
     */
    fun init(activity: FragmentActivity): PickerMediator {
        return PickerMediator(activity = activity)
    }

    /**
     * Initializes the picker with a [Fragment].
     *
     * @param fragment The [Fragment] in which the picker system will operate.
     * @return A [PickerMediator] instance to configure and manage picker operations.
     */
    fun init(fragment: Fragment): PickerMediator {
        return PickerMediator(fragment = fragment)
    }
}