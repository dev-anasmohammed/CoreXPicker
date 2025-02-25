# CoreXPicker

This library to handle picking media (Photos , Videos and Audio) file and Non Media files (Documents
and another types ) from storage without permission.

### Available Pickers:

[01 Gallery Picker (Photo & Videos)](#gallery-picker)<br/>
[02 Google Photo Picker (Photo & Videos)](#google-photo-picker)<br/>
[03 Camera Picker (Photo & Videos)](#camera-picker)<br/>
[04 Audio Picker (Audio Files)](#audio-picker)<br/>
[05 Document Picker (Non Media Files)](#document-picker)<br/>

## Gallery Picker

``` 
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
