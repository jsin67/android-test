package com.jaggrat.sample.model

import android.arch.persistence.room.TypeConverters
import com.jaggrat.sample.database.ImageUrlTypeConverter

/**
 * Image data class for storing the thumbnail url.
 */
class ImageData(
        @TypeConverters(ImageUrlTypeConverter::class)
        val mainImageThumbnail: NewsPostImage
)
@TypeConverters(ImageUrlTypeConverter::class)
class NewsPostImage (val url: String?)
