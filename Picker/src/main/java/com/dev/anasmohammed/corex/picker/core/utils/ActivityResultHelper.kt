package com.dev.anasmohammed.corex.picker.core.utils

import android.app.Activity
import android.net.Uri
import androidx.activity.result.ActivityResult
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

fun handleActivityResult(
    pickerType: PickerType,
    onMediaPickedCallback: OnMediaPickedCallback?,
    result: ActivityResult
) {
    when (result.resultCode) {
        Activity.RESULT_OK -> {
            onMediaPickedCallback?.onExceedMaxLimit(pickerType.limit , false)
            onMediaPickedCallback?.onMediaPicked(true, getUris(result, pickerType.isMultiPick))
        }

        Activity.RESULT_CANCELED -> {
            onMediaPickedCallback?.onExceedMaxLimit(pickerType.limit , false)
            onMediaPickedCallback?.onMediaPicked(false, emptyList())
        }
    }
}

private fun getUris(result: ActivityResult, isMultiSelect: Boolean): List<Uri?> {
    if (isMultiSelect) {
        val mediaUris = mutableListOf<Uri?>()

        val clipData = result.data?.clipData
        if (clipData != null) {
            for (i in 0 until clipData.itemCount) {
                clipData.getItemAt(i).uri?.let { mediaUris.add(it) }
            }
        } else {
            result.data?.data?.let { mediaUris.add(it) }
        }
        return mediaUris.toList()
    } else {
        return listOf(result.data?.data)
    }
}
