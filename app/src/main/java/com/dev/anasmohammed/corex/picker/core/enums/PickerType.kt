package com.dev.anasmohammed.corex.picker.core.enums

import com.dev.anasmohammed.corex.picker.abstraction.Picker
import com.dev.anasmohammed.corex.picker.core.pickers.AudioPicker
import com.dev.anasmohammed.corex.picker.core.pickers.CameraPicker
import com.dev.anasmohammed.corex.picker.core.pickers.DocumentPicker
import com.dev.anasmohammed.corex.picker.core.pickers.GalleryPicker
import com.dev.anasmohammed.corex.picker.core.pickers.PhotoPickerByGoogle

/**
 * Represents the different types of pickers available in the application, each with its own
 * specific configuration and behavior for selecting various media types.
 *
 * @property mimeType The MIME type associated with the picker for filtering file types.
 * @property limit The maximum number of items that can be picked, applicable for multi-pick scenarios.
 * @property isMultiPick Indicates whether the picker allows selecting multiple items.
 * @property picker The concrete implementation of the [Picker] interface associated with this picker type.
 */
sealed class PickerType(
    open val mimeType: String = "*/*",
    open val limit: Int = 2,
    open val isMultiPick: Boolean = false,
    val picker: Picker
) {

    /**
     * Defines pickers for selecting media from the device gallery.
     *
     * @property isMultiPick Indicates if the picker supports selecting multiple items.
     * @property mimeType Specifies the type of media to be picked (e.g., photos, videos).
     */
    sealed class Gallery(isMultiPick: Boolean = false, mimeType: String = MimeType.ALL) :
        PickerType(
            mimeType = mimeType,
            isMultiPick = isMultiPick,
            picker = GalleryPicker()
        ) {
        //single
        data object SinglePhoto : Gallery(mimeType = MimeType.PHOTOS)
        data object SingleVideo : Gallery(mimeType = MimeType.VIDEOS)
        data object SinglePhotoOrVideo : Gallery(mimeType = MimeType.ALL)

        //Multi
        data object MultiPhotos : Gallery(mimeType = MimeType.PHOTOS, isMultiPick = true)
        data object MultiVideos : Gallery(mimeType = MimeType.VIDEOS, isMultiPick = true)
        data object MultiPhotosAndVideos : Gallery(mimeType = MimeType.ALL, isMultiPick = true)
    }

    /**
     * Defines pickers for using Google's Photo Picker API.
     *
     * @property limit Specifies the maximum number of items that can be selected.
     * @property mimeType Allows customization of MIME types for file filtering.
     */
    sealed class GooglePhotoPicker(mimeType: String = MimeType.ALL, limit: Int = 2) : PickerType(
        mimeType = mimeType,
        limit = limit,
        picker = PhotoPickerByGoogle()
    ) {
        //single
        data object SinglePhoto : GooglePhotoPicker()
        data object SingleVideo : GooglePhotoPicker()
        data object SinglePhotoOrVideo : GooglePhotoPicker()

        //Multi
        data class MultiPhotos(override val limit: Int) : GooglePhotoPicker(limit = limit)
        data class MultiVideos(override val limit: Int) : GooglePhotoPicker(limit = limit)
        data class MultiPhotosAndVideos(override val limit: Int) : GooglePhotoPicker(limit = limit)

        //Custom
        data class CustomMimeType(override val mimeType: String) :
            GooglePhotoPicker(mimeType = mimeType)
    }

    /**
     * Defines pickers for selecting audio files.
     *
     * @property isMultiPick Indicates if multiple audio files can be selected.
     */
    data class Audio(override val isMultiPick: Boolean = false) :
        PickerType(isMultiPick = isMultiPick, picker = AudioPicker())

    /**
     * Defines pickers for selecting documents of various types.
     *
     * @property mimeType Specifies the MIME type of the document to be picked.
     * @property isMultiPick Indicates if multiple documents can be selected.
     */
    sealed class Document(
        mimeType: String = MimeType.ALL_DOCUMENTS,
        isMultiPick: Boolean = false
    ) : PickerType(
        mimeType = mimeType,
        isMultiPick = isMultiPick,
        picker = DocumentPicker()
    ) {
        data class All(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.ALL_DOCUMENTS)

        data class Pdf(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.PDF)

        data class Word(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.WORD)

        data class WordXMl(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.WORD_XML)

        data class Excel(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.EXCEL)

        data class ExcelXml(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.EXCEL_XML)

        data class PowerPoint(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.POWERPOINT)

        data class PowerPointXml(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.POWERPOINT_XML)

        data class Zip(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.ZIP)

        data class Apk(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.APK)

        data class Json(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.JSON)

        data class Ebooks(override val isMultiPick: Boolean = false) :
            Document(isMultiPick = isMultiPick, mimeType = MimeType.EPUB)
    }

    sealed class Camera() : PickerType(picker = CameraPicker()){
        data object Photo : Camera()
        data object Video : Camera()
    }
}

