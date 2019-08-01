package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data

class VideosAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var videoList: List<Data> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_videos, p0, false))
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val video = videoList[position]
        Glide.with(context).load(video.images.w_still.url).into(viewHolder.mImageView)
    }

    fun setVideoList(data: List<Data>) {
        this.videoList = data
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mImageView = itemView.findViewById<ImageView>(R.id.imageView)
}
