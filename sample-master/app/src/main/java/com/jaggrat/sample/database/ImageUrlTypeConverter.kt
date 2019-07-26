package com.jaggrat.sample.database

import android.arch.persistence.room.TypeConverter
import com.jaggrat.sample.model.ImageData
import com.jaggrat.sample.model.NewsPostImage

/**
 * TypeConverter for image url to store the value in data and get the from database
 */
class ImageUrlTypeConverter {
    @TypeConverter
    fun toimageUrl(url: String): ImageData? {
        return ImageData(NewsPostImage(url))
    }

    @TypeConverter
    fun fromimageUrl(data: ImageData?): String? {
        return data!!.mainImageThumbnail.url
    }
}