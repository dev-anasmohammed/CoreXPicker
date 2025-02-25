package com.dev.anasmohammed.corex.picker

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.anasmohammed.corex.corexpicker.R
import com.dev.anasmohammed.corex.picker.core.CoreXPicker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

private fun useOfGallery(activity: AppCompatActivity) {
    CoreXPicker.init(activity)
        .setType(PickerType.Gallery.SinglePhoto)
        .setType(PickerType.Gallery.SingleVideo)
        .setType(PickerType.Gallery.SinglePhotoOrVideo)
        .setType(PickerType.Gallery.MultiPhotos)
        .setType(PickerType.Gallery.MultiVideos)
        .setType(PickerType.Gallery.MultiPhotosAndVideos)
//        .pick(object : OnMediaPickedCallback {
//            override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
//
//            }
//        })
        .pick(object : OnCameraCapturedCallback{
            override fun onCameraCaptured(bitmap: Bitmap?) {
                super.onCameraCaptured(bitmap)
            }

            override fun onVideoCaptured(uri: Uri?) {
                super.onVideoCaptured(uri)
            }
        })
}