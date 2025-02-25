package com.dev.anasmohammed.corex.picker.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

/**
 * Responsible for executing the configured picker operation. It initializes
 * the picker with the required parameters and triggers the picking process.
 *
 * @param fragmentActivity The optional [FragmentActivity] context for the operation.
 * @param fragment The optional [Fragment] context for the operation.
 * @param pickerType The desired [PickerType] for the operation.
 */
class PickerBuilder(
    fragmentActivity: FragmentActivity?,
    fragment: Fragment?,
    private val pickerType: PickerType,
) {
    private lateinit var activity: FragmentActivity

    init {
        if (fragmentActivity != null) {
            activity = fragmentActivity
        }
        // activity and fragment must not be null at same time
        if (fragmentActivity == null && fragment != null) {
            activity = fragment.requireActivity()
        }
    }

    /**
     * Executes the picker operation by initializing the picker with the provided
     * [PickerType] and invoking the picking process.
     *
     * @param callback Callback to handle the result of the picking operation.
     */
    fun pick(callback: OnMediaPickedCallback) {
        pickerType.picker.init(activity, pickerType , callback , null)
        pickerType.picker.pick()
    }

    /**
     * Executes the picker operation by initializing the picker with the provided
     * [PickerType] and invoking the picking process.
     *
     * @param callback Callback to handle the result of the capturing operation.
     */
    fun pick(callback: OnCameraCapturedCallback) {
        pickerType.picker.init(activity, pickerType , null , callback)
        pickerType.picker.pick()
    }
}