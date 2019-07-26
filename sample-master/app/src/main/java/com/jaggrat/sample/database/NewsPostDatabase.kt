package com.jaggrat.sample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.jaggrat.sample.model.NewsPostVertical

/**
 * Interface which provide methods for accessing Database Access Objects
 */
@Database(entities = [(NewsPostVertical::class)], version = 5)
@TypeConverters(ImageUrlTypeConverter::class)
abstract class NewsPostDatabase : RoomDatabase() {
    /**
     * Returns the Database Access Object to deal with NewsPostVertical.
     * @return the Database Access Object to deal with NewsPostVertical
     */
    abstract fun newsPostDao(): NewsPostDao
}