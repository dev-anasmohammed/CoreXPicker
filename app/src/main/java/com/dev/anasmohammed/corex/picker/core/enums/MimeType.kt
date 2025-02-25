package com.dev.anasmohammed.corex.picker.core.enums

/**
 * The MimeType object provides a collection of constants representing MIME types
 * for various file formats, including images, documents, audio, and more.
 * These constants are used to filter and specify the type of files allowed during a picking operation.
 */
object MimeType {
    // A wildcard MIME type allowing all file types.
    const val ALL = "*/*"

    //Images
    const val PHOTOS = "image/*"
    const val VIDEOS = "video/*"
    const val GIF = "image/gif"

    //Documents
    const val ALL_DOCUMENTS = "application/*"
    const val WORD = "application/msword"  // .doc
    const val WORD_XML = "application/vnd.openxmlformats-officedocument.wordprocessingml.document" // .docx
    const val EXCEL = "application/vnd.ms-excel" // .xls
    const val EXCEL_XML = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" // .xlsx
    const val POWERPOINT = "application/vnd.ms-powerpoint" // .ppt
    const val POWERPOINT_XML = "application/vnd.openxmlformats-officedocument.presentationml.presentation" // .pptx
    const val ZIP = "application/zip"
    const val APK = "application/vnd.android.package-archive"
    const val PDF = "application/pdf"
    const val JSON = "application/json"

    //EPUB
    const val EPUB = "application/epub+zip" // EPUB e-books

    //Audio
    const val ALL_AUDIO = "audio/*" // EPUB e-books
}