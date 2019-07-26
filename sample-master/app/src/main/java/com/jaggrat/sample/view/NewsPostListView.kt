package com.jaggrat.sample.view

import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostVertical

interface NewsPostListView : BaseView {

    fun updateUI(newsPost : List<NewsPostBase>)
    fun showDetail(newsPost : List<NewsPostVertical>)
    fun showLoading()
    fun hideLoading()
    fun showError(error : String)
}
