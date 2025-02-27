package com.dev.anasmohammed.corex.picker

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dev.anasmohammed.corex.corexpicker.R
import com.dev.anasmohammed.corex.picker.core.CoreXPicker
import com.dev.anasmohammed.corex.picker.core.callback.OnMediaPickedCallback
import com.dev.anasmohammed.corex.picker.core.enums.PickerType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generalUsage()
    }

    private fun generalUsage(){
        CoreXPicker.init(this)
            .setType(PickerType.GooglePhotoPicker.SingleVideo)
            .pick(object : OnMediaPickedCallback{
                override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {
                    Log.e(javaClass.simpleName,"ImagePicked status: $isSuccess , uri/uris: $result")
                }

                override fun onExceedMaxLimit(maxLimit: Int, isExceed: Boolean) {
                    super.onExceedMaxLimit(maxLimit, isExceed)
                    Log.e(javaClass.simpleName,"onExceedMaxLimit isExceed: $isExceed , maxLimit: $maxLimit")
                }
            })
    }
}