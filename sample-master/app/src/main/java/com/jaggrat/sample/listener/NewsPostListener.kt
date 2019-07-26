package com.jaggrat.sample.listener

import com.jaggrat.sample.model.NewsPostViewModel

/**
 * On tapped listener
 */
interface NewsPostListener {

    /**
     * On tapped listener for new row.
     */
    fun onNewsPostTapped(model : NewsPostViewModel)
}