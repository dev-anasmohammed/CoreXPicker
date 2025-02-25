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
.setType(PickerType.[PickerYouWant].[Operation])
```kotlin
    .setType(PickerType.Gallery.SinglePhoto)
    .setType(PickerType.GooglePhotoPicker.MultiPhotos(10))
    .setType(PickerType.Document.All())
```

2- Handle the callback of picking
<br/> [For All Pickers]
```kotlin
    .pick(object : OnMediaPickedCallback {
        override fun onMediaPicked(isSuccess: Boolean, result: List<Uri?>) {

    }
})

```
[For Camera Picker]
```kotlin
     .pick(object : OnCameraCapturedCallback{
         override fun onCameraCaptured(bitmap: Bitmap?) {
            super.onCameraCaptured(bitmap)
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

## Google Photo Picker

## Camera Picker

## Audio Picker

## Document Picker
