package com.jaggrat.sample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaggrat.sample.R
import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostVertical

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<NewsPostBase>()

    override fun getItemCount(): Int = newsList.size

    fun setNewsUIData(list: List<NewsPostBase>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    private fun inflateView(parent: ViewGroup, id: Int): View {
        return LayoutInflater.from(parent.context).inflate(id, parent, false)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return when (viewType) {
            NewsPostBase.NEWS_POST_VERTICAL -> VerticalNewViewHolder(inflateView(parent, R.layout.list_item_vertical))
            else -> HorizontalNewsListViewHolder(inflateView(parent, R.layout.news_horizontal))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(newsList[position] is NewsPostVertical) {
            return NewsPostBase.NEWS_POST_VERTICAL
        }
        return NewsPostBase.NEWS_POST_HORIZONTAL
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.drawUI(newsList[position])
    }

    abstract class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun drawUI(template: NewsPostBase)
    }
}