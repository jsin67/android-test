package com.jaggrat.sample.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostHorizontal
import kotlinx.android.synthetic.main.news_horizontal.view.*

class HorizontalNewsListViewHolder(itemView: View) : NewsAdapter.NewsViewHolder(itemView) {

    private val adapter = HorizontalNewsViewAdapter()

    init {
        itemView.horizontal_news.let {
            it.layoutManager = LinearLayoutManager(it.context, RecyclerView.HORIZONTAL, false)
            LinearSnapHelper().attachToRecyclerView(it)
            it.adapter = adapter
        }
    }

    override fun drawUI(template: NewsPostBase) {
        if (template is NewsPostHorizontal) {
            adapter.setNews(template.news)
        }
    }
}