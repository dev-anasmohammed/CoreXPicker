package com.dev.anasmohammed.corex.picker.core.pickers

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType
import com.dev.anasmohammed.corex.picker.core.utils.startActivityForResult
import com.dev.anasmohammed.corex.picker.core.utils.handleActivityResult

/**
 * GalleryPicker is a concrete implementation of [Picker] for selecting photos and videos from the gallery.
 */
class GalleryPicker : Picker {

    private lateinit var imageGalleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickerType: PickerType

    /**
     * Initializes the gallery picker.
     *
     * @param activity The [FragmentActivity] where the picker is used.
     * @param pickerType The type of picker operation, defined by [PickerType].
     * @param onMediaPickedCallback Callback to handle the result of the picking operation.
     */

    override fun init(
        activity: FragmentActivity,
        pickerType: PickerType,
        onMediaPickedCallback: OnMediaPickedCallback?,
        onCameraCapturedCallback: OnCameraCapturedCallback?
    ) {
        this.pickerType = pickerType
        imageGalleryLauncher = activity.startActivityForResult("") { result ->
            handleActivityResult(pickerType, onMediaPickedCallback, result)
        }
    }

    /**
     * Launches the gallery picker intent to select media files from the gallery.
     */
    override fun pick() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        ).apply {
            type = pickerType.mimeType
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, pickerType.isMultiPick)
        }

        imageGalleryLauncher.launch(intent)
    }
}


