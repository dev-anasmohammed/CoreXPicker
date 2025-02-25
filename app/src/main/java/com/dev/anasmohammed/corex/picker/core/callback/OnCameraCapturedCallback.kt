package com.dev.anasmohammed.corex.picker.core.callback

import android.graphics.Bitmap
import android.net.Uri
import com.dev.anasmohammed.corex.picker.core.enums.PickerType.Camera
import com.dev.anasmohammed.corex.picker.core.pickers.CameraPicker

/**
 * OnCameraCapturedCallback is a callback interface for handling the result of a camera capturing operation.
 */
interface OnCameraCapturedCallback {
    /**
     * Called when picking from [Camera.Photo] by using [CameraPicker].
     *
     * @param bitmap A bitmap indicating the captured photo from camera app.
     */
    fun onCameraCaptured(bitmap: Bitmap?) {}

    /**
     * Called when picking from [Camera.Video] by using [CameraPicker].
     *
     * @param uri A uri indicating the captured video from camera app.
     */
    fun onVideoCaptured(uri: Uri?) {}
}