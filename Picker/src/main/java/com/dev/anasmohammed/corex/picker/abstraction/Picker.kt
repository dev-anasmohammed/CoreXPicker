package com.dev.anasmohammed.corex.picker.abstraction

import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

/**
 * The Picker interface defines the contract for media picker implementations.
 */
interface Picker {
    /**
     * Initializes the picker with the required parameters.
     *
     * @param activity The [FragmentActivity] where the picker is used.
     * @param pickerType The type of picker operation, defined by [PickerType].
     * @param onMediaPickedCallback Callback to handle the result of the picking operation.
     * @param onCameraCapturedCallback Callback to handle the result of the capturing operation.
     */
    fun init(
        activity: FragmentActivity,
        pickerType: PickerType,
        onMediaPickedCallback: OnMediaPickedCallback?,
        onCameraCapturedCallback: OnCameraCapturedCallback?,
    )

    /**
     * Launches the picker to perform the picking operation.
     */
    fun pick()
}