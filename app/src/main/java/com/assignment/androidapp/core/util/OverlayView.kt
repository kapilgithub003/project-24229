/*
 * Copyright 2023 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.assignment.androidapp.core.util

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.assignment.androidapp.R
import com.google.mlkit.vision.face.Face
import kotlin.math.min

data class FaceDetectorResult(val faces: List<Face>) {
    fun detections(): List<FaceDetectionData> {
        return faces.map {
            FaceDetectionData(
                boundingBox = it.boundingBox,
                categoryName = "Face",
                score = it.trackingId?.toFloat() ?: 1.0f
            )
        }
    }
}

data class FaceDetectionData(
    val boundingBox: Rect,
    val categoryName: String,
    val score: Float
) {
    fun boundingBox(): Rect = boundingBox
    fun categories(): List<Category> = listOf(Category(categoryName, score))
}

data class Category(val categoryName: String, val score: Float) {
    fun categoryName(): String = categoryName
    fun score(): Float = score
}

class OverlayView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var results: FaceDetectorResult? = null
    private var boxPaint = Paint()
    private var textBackgroundPaint = Paint()
    private var textPaint = Paint()

    private var scaleFactor: Float = 1f
    private var bounds = Rect()

    private var cameraWidth: Int = 0
    private var cameraHeight: Int = 0

    init {
        initPaints()
    }

    fun clear() {
        results = null
        textPaint.reset()
        textBackgroundPaint.reset()
        boxPaint.reset()
        invalidate()
        initPaints()
    }

    private fun initPaints() {
        textBackgroundPaint.color = Color.BLACK
        textBackgroundPaint.style = Paint.Style.FILL
        textBackgroundPaint.textSize = 50f

        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f

        boxPaint.color = Color.GREEN
        boxPaint.strokeWidth = 8F
        boxPaint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        results?.let {
            for (detection in it.detections()) {
                val boundingBox = detection.boundingBox()

                // Apply scaling based on camera preview size
                val top = boundingBox.top * scaleFactor
                val bottom = boundingBox.bottom * scaleFactor
                val left = boundingBox.left * scaleFactor
                val right = boundingBox.right * scaleFactor

                // Convert to a RectF for drawing on the canvas
                val drawableRect = RectF(left, top, right, bottom)
                canvas.drawRect(drawableRect, boxPaint)

                // Display the category name and score
                val drawableText = detection.categoryName + " " + String.format("%.2f", detection.score)

                textBackgroundPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
                val textWidth = bounds.width()
                val textHeight = bounds.height()

                // Draw background for text to improve readability
                canvas.drawRect(
                    left,
                    top - textHeight - BOUNDING_RECT_TEXT_PADDING,
                    left + textWidth + BOUNDING_RECT_TEXT_PADDING,
                    top + BOUNDING_RECT_TEXT_PADDING,
                    textBackgroundPaint
                )

                // Draw the text with the score
                canvas.drawText(
                    drawableText,
                    left,
                    top - BOUNDING_RECT_TEXT_PADDING,
                    textPaint
                )
            }
        }
    }

    fun setResults(
        detectionResults: FaceDetectorResult,
        cameraPreviewWidth: Int,
        cameraPreviewHeight: Int,
    ) {
        results = detectionResults
        cameraWidth = cameraPreviewWidth
        cameraHeight = cameraPreviewHeight

        // Adjust the scale factor based on the camera preview size
        scaleFactor = min(width * 1f / cameraWidth, height * 1f / cameraHeight)
        invalidate()
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}

