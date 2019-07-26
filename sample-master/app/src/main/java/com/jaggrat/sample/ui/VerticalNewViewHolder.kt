package com.jaggrat.sample.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostVertical
import kotlinx.android.synthetic.main.list_item_vertical.view.*

class VerticalNewViewHolder(itemView: View) : NewsAdapter.NewsViewHolder(itemView) {
    override fun drawUI(template: NewsPostBase) {
        if (template is NewsPostVertical) {
            itemView.news_title.text = template.title

        val placeholder = ColorDrawable(Color.DKGRAY)
        val requestOptions = RequestOptions
                .placeholderOf(placeholder)
                .fitCenter()
                .optionalCenterCrop()

        Glide.with(itemView.context)
                .load(template.images.mainImageThumbnail.url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.thumbnail)
        }
    }
}