package com.jaggrat.sample.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.jaggrat.sample.database.ImageUrlTypeConverter

/**
 * data class for news item for vertical style.
 */
@Entity
data class NewsPostVertical (
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val title : String,
        val summary : String,
        @TypeConverters(ImageUrlTypeConverter::class)
        val images: ImageData): NewsPostBase