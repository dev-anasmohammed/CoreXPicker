package com.dev.anasmohammed.corex.picker.core.pickers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.MimeType.ALL_AUDIO
import com.dev.anasmohammed.corex.picker.core.enums.PickerType
import com.dev.anasmohammed.corex.picker.core.utils.handleActivityResult
import com.dev.anasmohammed.corex.picker.core.utils.startActivityForResult

/**
 * AudioPicker is a concrete implementation of [Picker] used for selecting audio.
 */
class AudioPicker : Picker {
    private lateinit var pickAudioLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickerType: PickerType

    /**
     * Initializes the audio picker.
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
        pickAudioLauncher = activity.startActivityForResult("") { result ->
            handleActivityResult(pickerType, onMediaPickedCallback, result)
        }
    }

    /**
     * Launches the audio picker intent to select audio.
     */
    override fun pick() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = ALL_AUDIO
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, pickerType.isMultiPick)
        }

        // Launch the intent
        pickAudioLauncher.launch(intent)
    }
}