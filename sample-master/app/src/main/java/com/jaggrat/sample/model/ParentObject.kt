package com.jaggrat.sample.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Parent object for news post.
 */
@Entity
data class ParentObject (
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val totalItems : Int,
        val content: List<NewsPostVertical>)