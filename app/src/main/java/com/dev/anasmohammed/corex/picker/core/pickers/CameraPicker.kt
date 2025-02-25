package com.dev.anasmohammed.corex.picker.core.pickers

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.IntentCompat
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType
import com.dev.anasmohammed.corex.picker.core.utils.startActivityForResult

/**
 * AudioPicker is a concrete implementation of [Picker] used for selecting audio.
 */
class CameraPicker : Picker {
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickerType: PickerType
    private lateinit var activity: FragmentActivity

    /**
     * Initializes the audio picker.
     *
     * @param activity The [FragmentActivity] where the picker is used.
     * @param pickerType The type of picker operation, defined by [PickerType].
     * @param onCameraCapturedCallback Callback to handle the result of the capturing operation.
     */
    override fun init(
        activity: FragmentActivity,
        pickerType: PickerType,
        onMediaPickedCallback: OnMediaPickedCallback?,
        onCameraCapturedCallback: OnCameraCapturedCallback?
    ) {
        this.pickerType = pickerType
        this.activity = activity
        cameraLauncher = activity.startActivityForResult("") { result ->
            when (pickerType as PickerType.Camera) {
                PickerType.Camera.Photo -> {
                    val bitmap = result.data?.let {
                        IntentCompat.getParcelableExtra(
                            it, "data", Bitmap::class.java
                        )
                    }
                    onCameraCapturedCallback?.onCameraCaptured(bitmap)
                }

                PickerType.Camera.Video -> {
                    onCameraCapturedCallback?.onVideoCaptured(result.data?.data)
                }
            }
        }
    }

    /**
     * Launches the audio picker intent to select audio.
     */
    override fun pick() {
        val intent = when (pickerType as PickerType.Camera) {
            PickerType.Camera.Photo -> {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            }

            PickerType.Camera.Video -> {
                Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            }
        }

        // Start the camera activity using the registered launcher
        if (::cameraLauncher.isInitialized) {
            cameraLauncher.launch(intent)
        } else {
            Log.e("CameraPicker", "CameraPicker is not initialized. Call init() first.")
        }
    }
}