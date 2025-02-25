package com.dev.anasmohammed.corex.picker

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.anasmohammed.corex.corexpicker.R
import com.dev.anasmohammed.corex.picker.core.CoreXPicker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.MimeType
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

private fun useOfGallery(activity: AppCompatActivity) {
    CoreXPicker.init(activity)
        .setType(PickerType.Document.All(isMultiPick = true))
        .setType(PickerType.Document.Pdf(isMultiPick = true))
        .setType(PickerType.Document.Apk(isMultiPick = true))
        .setType(PickerType.Document.Zip(isMultiPick = true))
        .setType(PickerType.Document.Ebooks(isMultiPick = true))
        .setType(PickerType.Document.Word(isMultiPick = true))
        .setType(PickerType.Document.WordXMl(isMultiPick = true))
        .setType(PickerType.Document.Excel(isMultiPick = true))
        .setType(PickerType.Document.ExcelXml(isMultiPick = true))
        .setType(PickerType.Document.PowerPoint(isMultiPick = true))
        .setType(PickerType.Document.PowerPointXml(isMultiPick = true))
        .pick(object : OnMediaPickedCallback {
            override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

            }
        })
}