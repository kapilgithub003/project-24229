package com.assignment.androidapp.presentation.face_recognition

import androidx.lifecycle.ViewModel
import com.assignment.androidapp.core.util.FaceDetectorHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaceDetectorViewModel @Inject constructor() : ViewModel() {
    private var _delegate: Int = FaceDetectorHelper.DELEGATE_CPU
    private var _threshold: Float = FaceDetectorHelper.THRESHOLD_DEFAULT

    val currentDelegate: Int get() = _delegate
    val currentThreshold: Float get() = _threshold

    fun setDelegate(delegate: Int) {
        _delegate = delegate
    }

    fun setThreshold(threshold: Float) {
        _threshold = threshold
    }
}
