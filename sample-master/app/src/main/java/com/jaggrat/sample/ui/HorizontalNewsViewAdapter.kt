package com.jaggrat.sample.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jaggrat.sample.R
import com.jaggrat.sample.model.NewsPostVertical
import kotlinx.android.synthetic.main.list_item_horizontal.view.*

class HorizontalNewsViewAdapter: RecyclerView.Adapter<HorizontalNewsViewAdapter.HorizontalNewsViewHolder>() {

    private val news = mutableListOf<NewsPostVertical>()

    override fun getItemCount(): Int = news.size

    fun setNews(list: List<NewsPostVertical>) {
        news.clear()
        news.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_horizontal, parent, false)
        return HorizontalNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalNewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    class HorizontalNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: NewsPostVertical) {
            itemView.hoz_title.text = news.title
            val placeholder = ColorDrawable(Color.DKGRAY)
            val requestOptions = RequestOptions
                    .placeholderOf(placeholder)
                    .fitCenter()
                    .optionalCenterCrop()

            Glide.with(itemView.context)
                    .load(news.images.mainImageThumbnail.url)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemView.horz_thumbnail)
        }
    }
}