package com.dev.anasmohammed.corex.picker.core.pickers

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

/**
 * PhotoPickerByGoogle is a concrete implementation of [Picker] using Google's photo picker API.
 */
class PhotoPickerByGoogle : Picker {

    private lateinit var pickMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickerType: PickerType

    /**
     * Initializes the Google photo picker.
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
        pickMediaLauncher = activity.activityResultRegistry.register(
            "GooglePhotoPicker", ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            if (uri == null) {
                onMediaPickedCallback?.onMediaPicked(isSuccess = false, emptyList())
            } else {
                onMediaPickedCallback?.onMediaPicked(isSuccess = true, listOf(uri))
            }
        }

        pickMultipleMediaLauncher = activity.activityResultRegistry.register(
            "GooglePhotoPicker", ActivityResultContracts.PickMultipleVisualMedia(pickerType.limit)
        ) { uris ->
            if (uris.isEmpty()) {
                onMediaPickedCallback?.onMediaPicked(isSuccess = false, emptyList())
            } else {
                val finalUris = mutableListOf<Uri?>()
                finalUris.addAll(uris)

                //check if user exceed the max limit
                if (finalUris.size > pickerType.limit) {
                    if (pickerType.limit in 0..finalUris.size) {
                        finalUris.subList(pickerType.limit, finalUris.size).clear()
                    }
                }

                onMediaPickedCallback?.onMediaPicked(isSuccess = true, finalUris)
                onMediaPickedCallback?.onExceedMaxLimit(
                    pickerType.limit,
                    finalUris.size > pickerType.limit
                )
            }
        }
    }

    /**
     * Launches the appropriate picker based on the specified [PickerType.GooglePhotoPicker].
     */
    override fun pick() {
        when (pickerType as PickerType.GooglePhotoPicker) {
            //Single
            is PickerType.GooglePhotoPicker.SinglePhoto -> {
                pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            is PickerType.GooglePhotoPicker.SingleVideo -> {
                pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            }

            is PickerType.GooglePhotoPicker.SinglePhotoOrVideo -> {
                pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            }

            //Multi
            is PickerType.GooglePhotoPicker.MultiPhotos -> {
                pickMultipleMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            is PickerType.GooglePhotoPicker.MultiVideos -> {
                pickMultipleMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            }

            is PickerType.GooglePhotoPicker.MultiPhotosAndVideos -> {
                pickMultipleMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            }

            is PickerType.GooglePhotoPicker.CustomMimeType -> {
                pickMediaLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.SingleMimeType(
                            pickerType.mimeType
                        )
                    )
                )
            }
        }
    }
}