package com.dev.anasmohammed.corex.picker.core.utils

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

/**
 * Extension function for [FragmentActivity] to start an activity for result.
 *
 * Registers an activity result launcher that starts an activity and handles its result.
 * The provided `postForResult` callback is invoked with the [ActivityResult] once the activity finishes.
 *
 * @param key A unique key to register this result.
 * @param postForResult A callback to handle the result when the activity finishes.
 * @return An [ActivityResultLauncher] for starting the activity.
 */
fun FragmentActivity.startActivityForResult(
    key: String, postForResult: (ActivityResult) -> Unit
): ActivityResultLauncher<Intent> {
    return activityResultRegistry.register(
        key, ActivityResultContracts.StartActivityForResult()
    ) { result ->
        postForResult(result)
    }
}