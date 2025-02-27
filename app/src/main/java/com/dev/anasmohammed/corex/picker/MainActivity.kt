package com.dev.anasmohammed.corex.picker

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dev.anasmohammed.corex.corexpicker.databinding.ActivityMainBinding
import com.dev.anasmohammed.corex.picker.core.CoreXPicker
import com.dev.anasmohammed.corex.picker.core.callback.OnCameraCapturedCallback
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGalleryPicker.setOnClickListener {
            galleryUsage()
        }

        binding.btnGooglePhotoPicker.setOnClickListener {
            googlePhotoPickerUsage()
        }

        binding.btnCameraPicker.setOnClickListener {
            //cameraUsage()
        }

        binding.btnAudioPicker.setOnClickListener {
            audioPickerUsage()
        }

        binding.btnDocumentPicker.setOnClickListener {
            documentPicker()
        }
    }

    /** Gallery **/
    private fun galleryUsage() {
        CoreXPicker.init(this)
            .setType(PickerType.Gallery.SinglePhoto)
            .pick(object : OnMediaPickedCallback {
                override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
                    Log.e(
                        javaClass.simpleName, "ImagePicked status: $isSuccess , uri/uris: $result"
                    )
                }
            })
    }

    /** GooglePhotoPicker **/
    private fun googlePhotoPickerUsage() {
        CoreXPicker.init(this)
            .setType(PickerType.GooglePhotoPicker.SinglePhoto)
            .pick(object : OnMediaPickedCallback {
                override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
                    Log.e(
                        javaClass.simpleName, "ImagePicked status: $isSuccess , uri/uris: $result"
                    )
                }

                override fun onExceedMaxLimit(maxLimit: Int, isExceed: Boolean) {
                    super.onExceedMaxLimit(maxLimit, isExceed)
                    // handle if the photos exceed the limit that developer specify
                    // This used special to handle Huawei devices as limit not work for device that
                    // didn't support google play services
                    Log.e(
                        javaClass.simpleName,
                        "onExceedMaxLimit isExceed: $isExceed , maxLimit: $maxLimit"
                    )
                }
            })
    }

    /** Camera **/
    //TODO handle permission before pick
    private fun cameraUsage() {
        CoreXPicker.init(this)
            .setType(PickerType.Camera.Photo)
            .pick(object : OnCameraCapturedCallback {
                override fun onPhotoCaptured(bitmap: Bitmap?) {
                    super.onPhotoCaptured(bitmap)
                    Log.e(javaClass.simpleName, "onPhotoCaptured")
                }

                override fun onVideoCaptured(uri: Uri?) {
                    super.onVideoCaptured(uri)
                    Log.e(javaClass.simpleName, "onVideoCaptured uri: $uri")
                }
            })
    }

    /** AudioPicker **/
    private fun audioPickerUsage() {
        CoreXPicker.init(this)
            .setType(PickerType.Audio())
            .pick(object : OnMediaPickedCallback {
                override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
                    Log.e(
                        javaClass.simpleName, "AudioPicked status: $isSuccess , uri/uris: $result"
                    )
                }
            })
    }

    /** Document **/
    private fun documentPicker() {
        CoreXPicker.init(this)
            .setType(PickerType.Document.All())
            .pick(object : OnMediaPickedCallback {
                override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
                    Log.e(
                        javaClass.simpleName,
                        "DocumentPicked status: $isSuccess , uri/uris: $result"
                    )
                }
            })
    }
}