package com.jaggrat.sample.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jaggrat.sample.model.NewsPostVertical


/**
 * The database object in charge of inserting NewsPost objects and retrieving them from Database.
 * @property all the list of all NewsPosts in database
 */
@Dao
interface NewsPostDao {
    @get:Query("SELECT * FROM NewsPostVertical")
    val all: List<NewsPostVertical>


    @Insert
    fun insertAll(posts: List<NewsPostVertical>)

    @Query("Delete FROM NewsPostVertical")
    fun deleteAll()
}