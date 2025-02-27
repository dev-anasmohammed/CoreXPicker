package com.dev.anasmohammed.corex.picker.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

/**
 * Mediates between the picker initialization and configuration steps.
 * This class is responsible for setting the desired [PickerType] and
 * returning a [PickerBuilder] to handle picker operations.
 *
 * @param activity The [FragmentActivity] context for picker operations.
 * @param fragment The [Fragment] context for picker operations.
 */
class PickerMediator(
    private val activity: FragmentActivity? = null,
    private val fragment: Fragment? = null,
) {
    /**
     * Sets the type of picker operation and returns a [PickerBuilder] for
     * further configuration and execution.
     *
     * @param pickerType The desired [PickerType] for the operation.
     * @return A [PickerBuilder] instance to handle picker execution.
     */
    fun setType(pickerType: PickerType): PickerBuilder {
        return PickerBuilder(activity, fragment, pickerType)
    }
}