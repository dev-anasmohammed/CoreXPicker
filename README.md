# CoreXPicker

This library to handle picking media (Photos , Videos and Audio) file and Non Media files (Documents
and another types ) from storage without permission.

### Available Pickers:

[00 General Usage](#general-usage)<br/>
[01 Gallery Picker (Photo & Videos)](#gallery-picker)<br/>
[02 Google Photo Picker (Photo & Videos)](#google-photo-picker)<br/>
[03 Camera Picker (Photo & Videos)](#camera-picker)<br/>
[04 Audio Picker (Audio Files)](#audio-picker)<br/>
[05 Document Picker (Non Media Files)](#document-picker)<br/>

## General Usage

Full Usage of the library
<br/>

```kotlin
    CoreXPicker.init(activity)
      .setType(PickerType.Gallery.SinglePhoto)
      .pick(object : OnMediaPickedCallback {
         override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

         }
      })
```

1- Set the picker type you want to set
<br/>
<br/>
.setType(PickerType.[PickerYouWant].[Operation])

```kotlin
    .setType(PickerType.Gallery.SinglePhoto)
    .setType(PickerType.GooglePhotoPicker.MultiPhotos(10))
    .setType(PickerType.Document.All())
```

2- Handle the callback of picking
<br/>
<br/> [For All Pickers]

```kotlin
    .pick(object : OnMediaPickedCallback {
       override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

      }
    })
```

[For Camera Picker]

```kotlin
     .pick(object : OnCameraCapturedCallback {
        override fun onPhotoCaptured(bitmap: Bitmap?) {
           super.onPhotoCaptured(bitmap)
        }
 
        override fun onVideoCaptured(uri: Uri?) {
            super.onVideoCaptured(uri)
        }
     })
```

## Gallery Picker

```kotlin
    CoreXPicker.init(activity)
     .setType(PickerType.Gallery.SinglePhoto)
     .pick(object : OnMediaPickedCallback {
         override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

         }
    })
```

All Operations on Gallery Picker
<br/>

```kotlin
    .setType(PickerType.Gallery.SinglePhoto)
    .setType(PickerType.Gallery.SingleVideo)
    .setType(PickerType.Gallery.SinglePhotoOrVideo)
    .setType(PickerType.Gallery.MultiPhotos)
    .setType(PickerType.Gallery.MultiVideos)
    .setType(PickerType.Gallery.MultiPhotosAndVideos)
```

## Google Photo Picker

```kotlin
    CoreXPicker.init(activity)
     .setType(PickerType.GooglePhotoPicker.SinglePhoto)
     .pick(object : OnMediaPickedCallback {
        override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

        }

        override fun onExceedMaxLimit(maxLimit: Int, isExceed: Boolean) {
            super.onExceedMaxLimit(maxLimit, isExceed)
            // handle if the photos exceed the limit that developer specify
            // This used special to handle Huawei devices as limit not work for device that
            // didn't support google play services
        }
    })
```

All Operations on Google Photo Picker
<br/>

```kotlin
    .setType(PickerType.GooglePhotoPicker.SinglePhoto)
    .setType(PickerType.GooglePhotoPicker.SingleVideo)
    .setType(PickerType.GooglePhotoPicker.SinglePhotoOrVideo)
    .setType(PickerType.GooglePhotoPicker.MultiPhotos(3))
    .setType(PickerType.GooglePhotoPicker.MultiVideos(4))
    .setType(PickerType.GooglePhotoPicker.MultiPhotosAndVideos(4))
    .setType(PickerType.GooglePhotoPicker.CustomMimeType(MimeType.GIF))
    .setType(PickerType.GooglePhotoPicker.CustomMimeType("*/*"))
```

## Camera Picker

```kotlin
    CoreXPicker.init(activity)
      .setType(PickerType.Camera.Photo)
      .pick(object : OnCameraCapturedCallback {
         override fun onPhotoCaptured(bitmap: Bitmap?) {
             super.onPhotoCaptured(bitmap)

         }

         override fun onVideoCaptured(uri: Uri?) {
             super.onVideoCaptured(uri)

         }
      })
```

All Operations on Camera Picker
<br/>

```kotlin
    .setType(PickerType.Camera.Photo)
    .setType(PickerType.Camera.Video)
```

## Audio Picker

```kotlin
    CoreXPicker.init(activity)
      .setType(PickerType.Audio())
      .pick(object : OnMediaPickedCallback {
         override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

         }
      })
```

All Operations on Audio Picker
<br/>

```kotlin
    .setType(PickerType.Audio())
```

## Document Picker
```kotlin
    CoreXPicker.init(activity)
      .setType(PickerType.Document.All())
      .pick(object : OnMediaPickedCallback {
         override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

         }
         override fun onExceedMaxLimit(maxLimit: Int, isExceed: Boolean) {
              super.onExceedMaxLimit(maxLimit, isExceed)
              // handle if the photos exceed the limit that developer specify
              // This used special to handle Huawei devices as limit not work for device that
              // didn't support google play services
         }
      })
```

All Operations on Document Picker
<br/>

```kotlin
    .setType(PickerType.Document.All(isMultiPick = true))
    .setType(PickerType.Document.Pdf(isMultiPick = true))
    .setType(PickerType.Document.Word(isMultiPick = true))
    .setType(PickerType.Document.WordXMl(isMultiPick = true))
    .setType(PickerType.Document.Excel(isMultiPick = true))
    .setType(PickerType.Document.ExcelXml(isMultiPick = true))
    .setType(PickerType.Document.PowerPoint(isMultiPick = true))
    .setType(PickerType.Document.PowerPointXml(isMultiPick = true))
    .setType(PickerType.Document.Apk(isMultiPick = true))
    .setType(PickerType.Document.Zip(isMultiPick = true))
    .setType(PickerType.Document.Ebooks(isMultiPick = true))
    .setType(PickerType.Document.Json(isMultiPick = true))
```