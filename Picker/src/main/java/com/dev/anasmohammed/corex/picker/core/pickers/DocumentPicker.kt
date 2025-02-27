package com.dev.anasmohammed.corex.picker.core.pickers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType
import com.dev.anasmohammed.corex.picker.core.utils.startActivityForResult
import com.dev.anasmohammed.corex.picker.core.utils.handleActivityResult

/**
 * DocumentPicker is a concrete implementation of [Picker] used for selecting documents.
 */
class DocumentPicker : Picker {

    private lateinit var pickDocumentLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickerType: PickerType

    /**
     * Initializes the document picker.
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
        pickDocumentLauncher = activity.startActivityForResult("") { result ->
            handleActivityResult(pickerType, onMediaPickedCallback, result)
        }
    }

    /**
     * Launches the document picker intent to select documents.
     */
    override fun pick() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = pickerType.mimeType
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, pickerType.isMultiPick)
        }

        // Launch the intent
        pickDocumentLauncher.launch(intent)
    }
}