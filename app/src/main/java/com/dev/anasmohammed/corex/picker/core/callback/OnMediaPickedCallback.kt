package com.dev.anasmohammed.corex.picker.core.callback

import android.net.Uri

/**
 * OnMediaPickedCallback is a callback interface for handling the result of a media picking operation.
 */
interface OnMediaPickedCallback {
    /**
     * Called when the media picking operation completes.
     *
     * @param isSuccess A boolean indicating whether the operation was successful.
     * @param result A list of URIs representing the selected media files.
     * The list may contain null entries if no media was picked.
     */
    fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>)

    /**
     * Called when picking exceed the max limit under any condition.
     * The cause of doing this method to handle Huawei devices when they exceed the max limit
     * of items picked.
     * @param maxLimit A integer indicating max limit of picking items.
     * @param isExceed A boolean indicating whether picked items exceed the limit or not.
     */
    fun onExceedMaxLimit(maxLimit: Int, isExceed: Boolean)


}
